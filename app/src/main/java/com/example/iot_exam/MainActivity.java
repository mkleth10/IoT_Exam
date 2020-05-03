package com.example.iot_exam;

import androidx.appcompat.app.AppCompatActivity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.content.Intent;

import com.jjoe64.graphview.series.DataPoint;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    Button temp_button, hum_button, lpg_button, co_button, smoke_button;
    String json = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        new FetchData().execute();
        setContentView(R.layout.activity_main);

        temp_button = findViewById(R.id.temp);
        hum_button = findViewById(R.id.humidity);
        lpg_button = findViewById(R.id.lpg);
        co_button = findViewById(R.id.co);
        smoke_button = findViewById(R.id.smoke);

        temp_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(MainActivity.this, TempActivity.class);
                myIntent.putExtra("json", json);
                startActivity(myIntent);
            }
        });

        hum_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(MainActivity.this, HumActivity.class);
                myIntent.putExtra("json", json);
                startActivity(myIntent);
            }
        });

        lpg_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(MainActivity.this, LpgActivity.class);
                myIntent.putExtra("json", json);
                startActivity(myIntent);
            }
        });

        co_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(MainActivity.this, CoActivity.class);
                myIntent.putExtra("json", json);
                startActivity(myIntent);
            }
        });

        smoke_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(MainActivity.this, TempActivity.class);
                myIntent.putExtra("json", json);
                startActivity(myIntent);
            }
        });
    }

    public class FetchData extends AsyncTask<Void, Void, String> {

        @Override
        protected String doInBackground(Void... voids) {
            try {
                URL url = new URL("https://api.thingspeak.com/channels/1040273/feeds.json?api_key=609FSRHQNGD8C485&results=10");
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                StringBuilder stringBuilder = new StringBuilder();
                String line;

                while((line = bufferedReader.readLine()) != null){
                    stringBuilder.append(line).append("\n");
                }

                bufferedReader.close();
                urlConnection.disconnect();

                return stringBuilder.toString();

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        protected void onPostExecute(String response){
            json = response;
        }
    }
}


