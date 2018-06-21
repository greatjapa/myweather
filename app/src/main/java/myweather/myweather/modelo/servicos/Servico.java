package myweather.myweather.modelo.servicos;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;

import myweather.myweather.modelo.Medicao;
import myweather.myweather.modelo.cidades.Cidade;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public abstract class Servico {

    // ID de cada cidade no servico.
    private HashMap<String, String> codigosPorCidade = new HashMap<>();

    protected void setCodigoCidade(String nomeCidade, String codigo) {
        this.codigosPorCidade.put(codigo, nomeCidade);
    }

    protected String getCodigoCidade(String nomeCidade) {
        return this.codigosPorCidade.get(nomeCidade);
    }

    /**
     * Busca os dados para uma dada cidade e a alimenta com as informacoes retornadas.
     */
    public void buscarDados(Cidade cidade) {
        String url = montaURL(cidade);
        Request request = new Request.Builder().url(url).build();
        JSONObject dados = this.invocar(request);
        Medicao medicao = new Medicao(this);

        // Por default, a hora de leitura é agora. Alguns servicos podem substituir esse horario
        // com o momento real de leitura do sensor, mas nem todos os servicos fornecem esse dado.
        medicao.setHoraLeitura(System.currentTimeMillis());

        try {
            this.alimentarMedicao(medicao, dados);
            System.out.println(medicao.toString());
        } catch (JSONException e) {
            throw new IllegalStateException("Erro ao parsear JSON do "
                    + "servico " + this.getNome() + " para cidade " + cidade.getNome(), e);
        }

        cidade.getMedicoes().add(medicao);
    }

    protected JSONObject invocar(Request request) {
        try {
            System.out.println("Invocando servico " + request.url().toString());
            Response retorno = new OkHttpClient().newCall(request).execute();
            return new JSONObject(retorno.body().string());
        } catch(IOException e) {
            throw new IllegalStateException("Erro ao invocar servico " + this.getNome()
                    + " com URL " + request.url().toString(), e);
        } catch(JSONException e) {
            throw new IllegalStateException("Erro ao parsear retorno do servico " + this.getNome()
                    + " com URL " + request.url().toString(), e);
        }
    }

    public abstract String getNome();
    protected abstract String montaURL(Cidade cidade);
    protected abstract void alimentarMedicao(Medicao medicao, JSONObject dados) throws JSONException;
}
