package myweather.myweather.modelo.servicos;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import myweather.myweather.modelo.Medicao;
import myweather.myweather.modelo.cidades.Cidade;

public class ServicoMetaweather extends Servico {

    public ServicoMetaweather() {
        super.setCodigoCidade("Rio", "455825"); // Hardcoded enquanto nao tem um banco.
    }

    @Override
    public String getNome() {
        return "Metaweather";
    }

    @Override
    protected String montaURL(Cidade cidade) {
        return "https://www.metaweather.com/api/location/" + super.getCodigoCidade(cidade.getNome());
    }

    @Override
    protected void alimentarMedicao(Medicao medicao, JSONObject dados) throws JSONException {
        JSONArray array = dados.getJSONArray("consolidated_weather");
        JSONObject first = array.getJSONObject(0);
        Double temp = first.getDouble("the_temp");
        medicao.setTemperaturaAtual(temp);
        Double umidade = first.getDouble("humidity");
        medicao.setUmidadeAtual(umidade);
    }
}

