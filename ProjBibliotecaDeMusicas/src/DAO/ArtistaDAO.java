package DAO;

import Model.Artista;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;

public class ArtistaDAO {
    ResultSet rs;
    PreparedStatement sql;
    Statement comando;
    Connection conexao;
    
    Artista art = new Artista();
   
    public void Conexao(){
        try{
            // Variáveis com os dados da conexão ao BD
            String nomeservidor = "localhost:3306";
            String nomebanco = "bibliotecamusical";
            String nomeusuario = "root";
            String senha= "";
            // especificação do driver a ser utilizado
            String nomedriver = "com.mysql.cj.jdbc.Driver";
            // ativa o driver
            Class.forName(nomedriver);
            // Criando a conexão ao Banco
            String url = "jdbc:mysql://"+nomeservidor+"/"+nomebanco+"?useTimezone=true&serverTimezone=UTC";
            conexao=DriverManager.getConnection(url,nomeusuario,senha);
            comando = conexao.createStatement();
            
        }
        catch (ClassNotFoundException e){
	       JOptionPane.showMessageDialog(null,"Driver não encontrado!");
            }
        catch (Exception e){
               JOptionPane.showMessageDialog(null," Erro ao conectar o Banco de Dados ");
            }

    }
    public void Cadastrar(String p_nome_artista, String p_alter_ego, String p_data_nasc, int p_ouvintes){
        art.setNome_Artista(p_nome_artista);
        art.setAlter_Ego(p_alter_ego);
        art.setData_Nasc(p_data_nasc);
        art.setOuvintes(p_ouvintes);
        try{
            sql = conexao.prepareStatement("Insert into Artistas (Nome_Artista, Alter_Ego, Data_Nasc, Ouvintes)"+"values (?,?,?,?)");
            sql.setString(1, art.getNome_Artista());
            sql.setString(2, art.getAlter_Ego());
            sql.setString(3, art.getData_Nasc());
            sql.setInt(4, art.getOuvintes());
            int reg = sql.executeUpdate();
            if (reg !=0){
                JOptionPane.showMessageDialog(null,"Dados cadastrados !!!");
            }
            else{
                JOptionPane.showMessageDialog(null,"Erro Sql !!!");
        }
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null,"Erro ao conectar Banco de Dados !!!");
        }
        
    }
    
    public void Listar(){
        try{
        // objeto sql recebe a instrução a ser executada
        sql=conexao.prepareStatement("select * from Artistas");
        // rs recebe uma lista de retorno pelo executequery
        rs=sql.executeQuery();
        // percorre a lista (enquanto houver o próximo)
        while(rs.next()){
            // rs busca cada registro da lista pelo nome do campo da tabela
            JOptionPane.showMessageDialog(null,"Nome_Artista: "+rs.getString("Nome_Artista")+ "\n Alter_Ego: "+rs.getString("Alter_Ego")+ "\n Data_Nasc: "+rs.getString("Data_Nasc")+ "\n Ouvintes: "+rs.getInt("Ouvintes"));
        }
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null,"Erro ao conectar Banco de Dados !!!");
        }
    }
   
      public String Consultar(int p_id){
          String dados="";
          try{
              sql = conexao.prepareStatement("Select * from Artistas where Id_Artista = ?");
              sql.setInt(1, p_id);
              rs = sql.executeQuery();
              if (rs.next()){
                  dados+=rs.getString("Nome_Artista");
                  dados+=";";
                  dados+=rs.getString("Alter_Ego");
                  dados+=";";
                  dados+=rs.getString("Data_Nasc");
                  dados+=";";
                  dados+=rs.getString("Ouvintes");
              }
              else{
                   JOptionPane.showMessageDialog(null,"Id não encontrado");
              }
              
          }
          catch(Exception e){
              JOptionPane.showMessageDialog(null,"Erro ao conectar Banco de Dados !!!");
          }
          return dados;
      } 
    
    
    public void Alterar(int p_id, String p_nome_artista, String p_alter_ego, String p_data_nasc, int p_ouvintes){
        try{
            sql = conexao.prepareStatement("update Artistas set Nome_Artista='"+p_nome_artista+"', Alter_Ego='"+p_alter_ego+"', Data_Nasc='"+p_data_nasc+"', Ouvintes='"+p_ouvintes+"' where Id_Artista="+p_id);
            int verifica=sql.executeUpdate();
            if(verifica>0){
                JOptionPane.showMessageDialog(null, "Registro alterado com sucesso");
            }else{
                JOptionPane.showMessageDialog(null, "Registro não alterado !!!");
            }
            
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Erro ao conectar Banco de dados");
        }
    }
    
    public void Excluir(int p_id){
        try{
            sql = conexao.prepareStatement("delete from Artistas where Id_Artista="+p_id);
            int verifica = sql.executeUpdate();
            if (verifica>0){
                JOptionPane.showMessageDialog(null, "Sucesso!!!");
            }else{
                JOptionPane.showMessageDialog(null, "Exclusão não sucedida!!!");
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Erro ao conectar Banco de dados");
        }
    }
    
    
}
