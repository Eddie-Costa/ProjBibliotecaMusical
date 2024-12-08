package DAO;

import Model.Musica;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;

public class MusicaDAO {
    ResultSet rs;
    PreparedStatement sql;
    Statement comando;
    Connection conexao;
    
    Musica msc = new Musica();
   
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
    public void Cadastrar(String p_nome_musica, String p_lancamento, String p_album, int p_ouvintes){
        msc.setNome_Musica(p_nome_musica);
        msc.setLancamento(p_lancamento);
        msc.setAlbum(p_album);
        msc.setOuvintes(p_ouvintes);
        try{
            sql = conexao.prepareStatement("Insert into Musicas (Nome_Musica, Lancamento, Album, Ouvintes)"+"values (?,?,?,?)");
            sql.setString(1, msc.getNome_Musica());
            sql.setString(2, msc.getLancamento());
            sql.setString(3, msc.getAlbum());
            sql.setInt(4, msc.getOuvintes());
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
        sql=conexao.prepareStatement("select * from Musicas");
        // rs recebe uma lista de retorno pelo executequery
        rs=sql.executeQuery();
        // percorre a lista (enquanto houver o próximo)
        while(rs.next()){
            // rs busca cada registro da lista pelo nome do campo da tabela
            JOptionPane.showMessageDialog(null,"Nome_Musica: "+rs.getString("Nome_Musica")+ "\n Lancamento: "+rs.getString("Lancamento")+ "\n Album: "+rs.getString("Album")+ "\n Ouvintes: "+rs.getInt("Ouvintes"));
        }
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null,"Erro ao conectar Banco de Dados !!!");
        }
    }
   
      public String Consultar(int p_id1){
          String dados="";
          try{
              sql = conexao.prepareStatement("Select * from Musicas where Id_Musica = ?");
              sql.setInt(1, p_id1);
              rs = sql.executeQuery();
              if (rs.next()){
                  dados+=rs.getString("Nome_Musica");
                  dados+=";";
                  dados+=rs.getString("Lancamento");
                  dados+=";";
                  dados+=rs.getString("Album");
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
    
    
    public void Alterar(int p_id1, String p_nome_musica, String p_lancamento, String p_album, int p_ouvintes){
        try{
            sql = conexao.prepareStatement("update musicas set Nome_Musica='"+p_nome_musica+"', Lancamento='"+p_lancamento+"', Album='"+p_album+"', Ouvintes='"+p_ouvintes+"' where Id_Musica="+p_id1);
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
    
    public void Excluir(int p_id1){
        try{
            sql = conexao.prepareStatement("delete from musicas where Id_Musica="+p_id1);
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
