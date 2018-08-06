package myweather.myweather.modelo.servicos;

import org.json.JSONException;
import org.json.JSONObject;

import myweather.myweather.modelo.Medicao;
import myweather.myweather.modelo.MedicaoUtils;
import myweather.myweather.modelo.cidades.Cidade;

public class Servico5Day extends Servico {

    public Servico5Day() {
        super.setCodigoCidade("Rio", "Rio_de_Janeiro"); // Hardcoded enquanto nao tem um banco.
    }

    @Override
    public String getNome() {
        return "5Day";
    }

    @Override
    protected String montaURL(Cidade cidade) {
        return "https://5dayweather.org/api.php?city="
                + super.getCodigoCidade(cidade.getNome());
    }

    @Override
    protected void alimentarMedicao(Medicao medicao, JSONObject dados) throws JSONException {
        JSONObject data = (dados.getJSONObject("data"));
        medicao.setTemperaturaAtual(MedicaoUtils.toCelsius(data.getDouble("temp")));
        medicao.setUmidadeAtual(data.getDouble("humidity"));
    }
}
