package Model;

public class Musica {
    private String Nome_Musica;
    private String Lancamento;
    private String Album;
    private int Ouvintes;
    
    //Metodos
    
    //Setters

    public void setNome_Musica(String Nome_Musica) {
        this.Nome_Musica = Nome_Musica;
    }

    public void setLancamento(String Lancamento) {
        this.Lancamento = Lancamento;
    }

    public void setAlbum(String Album) {
        this.Album = Album;
    }

    public void setOuvintes(int Ouvintes) {
        this.Ouvintes = Ouvintes;
    }
    
    //Getters

    public String getNome_Musica() {
        return Nome_Musica;
    }

    public String getLancamento() {
        return Lancamento;
    }

    public String getAlbum() {
        return Album;
    }

    public int getOuvintes() {
        return Ouvintes;
    }
    
}
