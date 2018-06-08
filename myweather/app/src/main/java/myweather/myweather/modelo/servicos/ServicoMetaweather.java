package myweather.myweather.modelo.servicos;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

import myweather.myweather.modelo.cidades.Cidade;

public class ServicoMetaweather extends Servico {

    // ID de cada cidade no servico.
    private HashMap<String, String> codigosPorCidade = new HashMap<>();

    public ServicoMetaweather() {
        codigosPorCidade.put("Rio", "455825"); // Hardcoded enquanto nao tem um banco.
    }

    @Override
    protected String getNome() {
        return "Metaweather";
    }

    @Override
    protected String montaURL(Cidade cidade) {
        String codigoCidade = this.codigosPorCidade.get(cidade.getNome());
        return "https://www.metaweather.com/api/location/" + codigoCidade;
    }

    @Override
    protected void alimentarDados(JSONObject dados, Cidade cidade) {
        try {
            JSONArray array = dados.getJSONArray("consolidated_weather");
            JSONObject first = array.getJSONObject(0);
            Double temp = first.getDouble("the_temp");
            cidade.setTemperaturaMedia(temp);
        } catch (JSONException e) {
            throw new IllegalStateException("Erro ao alimentar JSON na cidade " + cidade.getNome(), e);
        }
    }
}

