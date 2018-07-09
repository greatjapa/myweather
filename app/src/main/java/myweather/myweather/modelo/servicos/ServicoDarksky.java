package myweather.myweather.modelo.servicos;

import org.json.JSONException;
import org.json.JSONObject;

import myweather.myweather.modelo.Medicao;
import myweather.myweather.modelo.cidades.Cidade;

public class ServicoDarksky extends Servico {

    public ServicoDarksky() {
        super.setCodigoCidade("Rio", "-22.92,-43.26"); // Hardcoded enquanto nao tem um banco.
    }

    @Override
    public String getNome() {
        return "Darksky";
    }

    @Override
    protected String montaURL(Cidade cidade) {
        return "https://api.darksky.net/forecast/fe32c29e97826ede1c6b3fb2c15f99b4/"
                + super.getCodigoCidade(cidade.getNome())
                + "?exclude=minutely,hourly,daily,alerts,flags&units=si";
    }

    @Override
    protected void alimentarMedicao(Medicao medicao, JSONObject dados) throws JSONException {
        JSONObject currently = dados.getJSONObject("currently");
        medicao.setHoraLeitura(currently.getLong("time"));
        medicao.setTemperaturaAtual(currently.getDouble("temperature"));
        medicao.setUmidadeAtual(currently.getDouble("humidity") * 100);
    }
}
