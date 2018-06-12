package myweather.myweather.modelo;

import myweather.myweather.modelo.servicos.Servico;

public class Medicao {

    private Servico servicoOrigem;
    private Long horaLeitura;
    private Double temperaturaAtual;
    private Double umidadeAtual;

    public Medicao(Servico servicoOrigem) {
        this.servicoOrigem = servicoOrigem;
    }

    public Servico getServicoOrigem() {
        return servicoOrigem;
    }

    public Long getHoraLeitura() {
        return horaLeitura;
    }

    public void setHoraLeitura(Long horaLeitura) {
        this.horaLeitura = horaLeitura;
    }

    public Double getTemperaturaAtual() {
        return temperaturaAtual;
    }

    public void setTemperaturaAtual(Double temperaturaAtual) {
        this.temperaturaAtual = temperaturaAtual;
    }

    public Double getUmidadeAtual() {
        return umidadeAtual;
    }

    public void setUmidadeAtual(Double umidadeAtual) {
        this.umidadeAtual = umidadeAtual;
    }
}
