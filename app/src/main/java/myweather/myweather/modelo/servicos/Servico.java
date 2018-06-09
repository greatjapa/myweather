package myweather.myweather.modelo.servicos;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import myweather.myweather.modelo.cidades.Cidade;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public abstract class Servico {

    /**
     * Busca os dados para uma dada cidade e a alimenta com as informacoes retornadas.
     */
    public void buscarDados(Cidade cidade) {
        String url = montaURL(cidade);
        Request request = new Request.Builder().url(url).build();
        JSONObject dados = this.invocar(request);
        this.alimentarDados(dados, cidade);
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

    protected abstract String getNome();
    protected abstract String montaURL(Cidade cidade);
    protected abstract void alimentarDados(JSONObject dados, Cidade cidade);
}
