package myweather.myweather.modelo.servicos;

import org.json.JSONException;
import org.json.JSONObject;

import myweather.myweather.modelo.Medicao;
import myweather.myweather.modelo.cidades.Cidade;

public class ServicoYahooweather extends Servico {

    public ServicoYahooweather() {
        super.setCodigoCidade("Rio", "455825"); // Hardcoded enquanto nao tem um banco.
    }

    @Override
    public String getNome() {
        return "Yahooweather";
    }

    @Override
    protected String montaURL(Cidade cidade) {
        return "https://query.yahooapis.com/v1/public/yql?q=select%20"
                + "item.condition%2C%20atmosphere.humidity"
                + "%20from%20weather.forecast%20where%20woeid%20%3D%20"
                + super.getCodigoCidade(cidade.getNome())
                + "%20and%20u%20%3D%20%27c%27&format=json&env=store%3A%2F%2Fdatatables.org%2Falltableswithkeys";
    }

    @Override
    protected void alimentarMedicao(Medicao medicao, JSONObject dados) throws JSONException {
        //response.query.results.channel
        JSONObject channel = dados.getJSONObject("query").getJSONObject("results").getJSONObject("channel");
        JSONObject condition = channel.getJSONObject("item").getJSONObject("condition");

        // TODO: Usar (ou implementar) uma classe Util pra transformar essa String em Data
        // String dataLeitura = condition.getString("date");
        // medicao.setHoraLeitura(dataLeitura);

        medicao.setTemperaturaAtual(condition.getDouble("temp"));
        medicao.setUmidadeAtual(channel.getJSONObject("atmosphere").getDouble("humidity"));
    }
}
