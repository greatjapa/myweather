package myweather.myweather.modelo.servicos;

import org.json.JSONException;
import org.json.JSONObject;

import myweather.myweather.modelo.Medicao;
import myweather.myweather.modelo.cidades.Cidade;

public class ServicoWeatherbit extends Servico {

    public ServicoWeatherbit() {
        super.setCodigoCidade("Rio", "3451190"); // Hardcoded enquanto nao tem um banco.
    }

    @Override
    public String getNome() {
        return "Yahooweather";
    }

    @Override
    protected String montaURL(Cidade cidade) {
        return "http://api.weatherbit.io/v2.0/current?key=e7bffc44464c4ddf8fd46877af83d4b5&units=M&city_id="
                + super.getCodigoCidade(cidade.getNome());
    }

    @Override
    protected void alimentarMedicao(Medicao medicao, JSONObject dados) throws JSONException {
        JSONObject data = dados.getJSONArray("data").getJSONObject(0);
        medicao.setHoraLeitura(data.getLong("ts"));
        medicao.setTemperaturaAtual(data.getDouble("temp"));
        medicao.setUmidadeAtual(data.getDouble("rh"));
    }
}
