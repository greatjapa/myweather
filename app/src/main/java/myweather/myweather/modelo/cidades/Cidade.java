package myweather.myweather.modelo.cidades;

import java.lang.reflect.Array;
import java.util.ArrayList;

import myweather.myweather.modelo.Medicao;

public class Cidade {

    private String nome;
    private ArrayList<Medicao> medicoes;

    public Cidade(String nome) {
        this.nome = nome;
        this.medicoes = new ArrayList<>();
    }

    public String getNome() {
        return this.nome;
    }

    public ArrayList<Medicao> getMedicoes() {
        return medicoes;
    }

    public Double getTemperaturaMediaAtual() {
        if (medicoes.isEmpty())
            return null;

        double tempMedia = 0;
        for (Medicao medicao : medicoes)
            tempMedia += medicao.getTemperaturaAtual();
        return tempMedia/medicoes.size();
    }

    public Double getUmidadeMediaAtual() {
        if (medicoes.isEmpty())
            return null;

        double umidadeMedia = 0;
        for (Medicao medicao : medicoes)
            umidadeMedia += medicao.getUmidadeAtual();
        return umidadeMedia/medicoes.size();
    }
}
