package myweather.myweather;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.common.logging.Logger;

import myweather.myweather.modelo.cidades.Cidade;
import myweather.myweather.modelo.servicos.ServicoMetaweather;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button detalhesButton = this.findViewById(R.id.buttonID);
        detalhesButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                System.out.println("Clicando detalhesButton");
                Intent i = new Intent(MainActivity.this, DetailsActivity.class);
                startActivity(i);
            }
        });

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
            viewById.setText(rio.getTemperaturaMediaAtual().toString());

            viewById = activity.findViewById(R.id.value06ID);
            viewById.setText(rio.getUmidadeMediaAtual().toString());
        }
    }
}
