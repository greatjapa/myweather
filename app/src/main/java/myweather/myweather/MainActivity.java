package myweather.myweather;

import android.os.AsyncTask;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;


import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new GetTask(this).execute("https://www.metaweather.com/api/location/455825/");
    }

    public class GetTask extends AsyncTask<String, Void, String> {

        private MainActivity activity;
        private OkHttpClient client;

        GetTask(MainActivity activity) {
            this.client = new OkHttpClient();
            this.activity = activity;
        }

        @RequiresApi(api = Build.VERSION_CODES.KITKAT)
        protected String doInBackground(String... params) {
            Request request = new Request.Builder()
                    .url(params[0])
                    .build();

            try (Response response = client.newCall(request).execute()) {
                return response.body().string();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        protected void onPostExecute(String info) {
            try {
                JSONObject reader = new JSONObject(info);
                JSONArray array = reader.getJSONArray("consolidated_weather");
                JSONObject first = array.getJSONObject(0);
                Double temp = first.getDouble("the_temp");

                TextView viewById = activity.findViewById(R.id.value04ID);
                viewById.setText(temp.toString());

                TextView view = activity.findViewById(R.id.value05ID);
                view.setText(temp.toString());
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}
