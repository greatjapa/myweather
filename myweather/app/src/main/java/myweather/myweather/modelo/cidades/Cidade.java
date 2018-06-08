package myweather.myweather.modelo.cidades;

public class Cidade {

    private String nome;
    private Double temperaturaMedia;

    public Cidade(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return this.nome;
    }

    public Double getTemperaturaMedia() {
        return this.temperaturaMedia;
    }

    public void setTemperaturaMedia(Double temperaturaMedia) {
        this.temperaturaMedia = temperaturaMedia;
    }
}
