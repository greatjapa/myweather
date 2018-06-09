package myweather.myweather;

import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import myweather.myweather.modelo.cidades.Cidade;
import myweather.myweather.modelo.servicos.ServicoMetaweather;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {
            new GetTask(this).execute();
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }

    public class GetTask extends AsyncTask<String, Void, String> {

        private MainActivity activity;
        private Cidade rio = new Cidade("Rio");
        private ServicoMetaweather metaweather = new ServicoMetaweather();

        GetTask(MainActivity activity) {
            this.activity = activity;
        }

        @RequiresApi(api = Build.VERSION_CODES.KITKAT)
        protected String doInBackground(String... params) {
            metaweather.buscarDados(rio);
            return "ok";
        }

        protected void onPostExecute(String info) {
            TextView viewById = activity.findViewById(R.id.value01ID);
            viewById.setText(rio.getNome());

            viewById = activity.findViewById(R.id.value05ID);
            viewById.setText(rio.getTemperaturaMedia().toString());
        }
    }
}
