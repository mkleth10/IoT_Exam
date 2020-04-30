package com.example.iot_exam;

import androidx.appcompat.app.AppCompatActivity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.content.Intent;

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

    Button temp_button;
    Button hum_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        temp_button = findViewById(R.id.temp);
        hum_button = findViewById(R.id.humidity);

        temp_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(MainActivity.this, TempActivity.class);
                startActivity(myIntent);
            }
        });

        hum_button.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              Intent myIntent = new Intent(MainActivity.this, TempActivity.class);
              startActivity(myIntent);
            }
        });

    }

    class getDataFromThingspeak extends AsyncTask<Void, Void, String>{

        @Override
        protected void onPreExecute() {

        }

        @Override
        protected String doInBackground(Void... voids) {
            try{
                URL url = new URL("https://api.thingspeak.com/channels/1040273/feeds.json?api_key=609FSRHQNGD8C485&results=2");
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

            } catch (MalformedURLException e){
                e.printStackTrace();
            } catch (IOException e){
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String response) {
            try {
                JSONObject json = new JSONObject(response);
                JSONArray feeds = json.getJSONArray("feeds");

                JSONObject lastValues = feeds.getJSONObject(feeds.length()-1);

                int val = lastValues.getInt("field1");
                int time = lastValues.getInt("created_at");

            } catch(JSONException e){
                e.printStackTrace();
            }
        }
    }
}
