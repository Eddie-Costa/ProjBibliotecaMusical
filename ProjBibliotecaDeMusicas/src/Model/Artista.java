package Model;

public class Artista {
    private String Nome_Artista;
    private String Alter_Ego;
    private String Data_Nasc;
    private int Ouvintes;
    
    //Metodos
    
    //setters

    public void setNome_Artista(String Nome_Artista) {
        this.Nome_Artista = Nome_Artista;
    }

    public void setAlter_Ego(String Alter_Ego) {
        this.Alter_Ego = Alter_Ego;
    }

    public void setData_Nasc(String Data_Nasc) {
        this.Data_Nasc = Data_Nasc;
    }

    public void setOuvintes(int Ouvintes) {
        this.Ouvintes = Ouvintes;
    }
    
    //getters

    public String getNome_Artista() {
        return Nome_Artista;
    }

    public String getAlter_Ego() {
        return Alter_Ego;
    }

    public String getData_Nasc() {
        return Data_Nasc;
    }

    public int getOuvintes() {
        return Ouvintes;
    }
    
}
