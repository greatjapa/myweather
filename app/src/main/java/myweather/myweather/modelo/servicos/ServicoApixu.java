package myweather.myweather.modelo.servicos;

import org.json.JSONException;
import org.json.JSONObject;

import myweather.myweather.modelo.Medicao;
import myweather.myweather.modelo.cidades.Cidade;

public class ServicoApixu extends Servico {

    public ServicoApixu() {
        super.setCodigoCidade("Rio", "Rio_de_Janeiro"); // Hardcoded enquanto nao tem um banco.
    }

    @Override
    public String getNome() {
        return "Apixu";
    }

    @Override
    protected String montaURL(Cidade cidade) {
        return "http://api.apixu.com/v1/forecast.json?key=e8d7e36901604691964123225171808&days=10&q="
                + super.getCodigoCidade(cidade.getNome());
    }

    @Override
    protected void alimentarMedicao(Medicao medicao, JSONObject dados) throws JSONException {
        JSONObject current = dados.getJSONObject("current");
        medicao.setHoraLeitura(current.getLong("last_updated_epoch"));
        medicao.setTemperaturaAtual(current.getDouble("temp_c"));
        medicao.setUmidadeAtual(current.getDouble("humidity"));
    }
}
