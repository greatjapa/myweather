package myweather.myweather.modelo.servicos;

import org.json.JSONException;
import org.json.JSONObject;

import myweather.myweather.modelo.Medicao;
import myweather.myweather.modelo.cidades.Cidade;

public class ServicoOpenweather extends Servico {

    public ServicoOpenweather() {
        super.setCodigoCidade("Rio", "3451190"); // Hardcoded enquanto nao tem um banco.
    }

    @Override
    protected String getNome() {
        return "Openweather";
    }

    @Override
    protected String montaURL(Cidade cidade) {
        return "http://api.openweathermap.org/data/2.5/weather?APPID=a084eb19b35276e09856e1a9238ff3c5&units=metric&id="
                + super.getCodigoCidade(cidade.getNome());
    }

    @Override
    protected void alimentarMedicao(Medicao medicao, JSONObject dados) throws JSONException {
        medicao.setHoraLeitura(dados.getLong("dt"));
        JSONObject main = dados.getJSONObject("main");
        medicao.setTemperaturaAtual(main.getDouble("temp"));
        medicao.setUmidadeAtual(main.getDouble("humidity"));
    }
}
