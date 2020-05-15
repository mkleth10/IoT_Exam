package com.example.iot_exam;

import androidx.appcompat.app.AppCompatActivity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.content.Intent;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    Button room_jacob, room_mikkel_b, room_mikkel_l;
    String[] json = new String[3];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        new FetchData().execute();
        setContentView(R.layout.activity_main);

        room_jacob = findViewById(R.id.room_jacob);
        room_mikkel_b = findViewById(R.id.room_mikkel_b);
        room_mikkel_l = findViewById(R.id.room_mikkel_l);

        room_jacob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(MainActivity.this, RoomActivity.class);
                myIntent.putExtra("json", json[0]);
                startActivity(myIntent);
            }
        });

        room_mikkel_b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(MainActivity.this, RoomActivity.class);
                myIntent.putExtra("json", json[1]);
                startActivity(myIntent);
            }
        });

        room_mikkel_l.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(MainActivity.this, RoomActivity.class);
                myIntent.putExtra("json", json[2]);
                startActivity(myIntent);
            }
        });
    }

    public class FetchData extends AsyncTask<Void, Void, String> {

        @Override
        protected String doInBackground(Void... voids) {
            try {
                URL[] urls = new URL[]{
                        new URL("https://api.thingspeak.com/channels/1054207/feeds.json?api_key=LJU94M5HZ6B3Z8NU&results=10"),
                        new URL("https://api.thingspeak.com/channels/1054206/feeds.json?api_key=KU449K5FKMIVME7Q&results=10"),
                        new URL("https://api.thingspeak.com/channels/1040273/feeds.json?api_key=609FSRHQNGD8C485&results=10")
                };

                StringBuilder stringBuilder = new StringBuilder();

                for(int i = 0; i < urls.length; i++) {
                    HttpURLConnection urlConnection = (HttpURLConnection) urls[i].openConnection();
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                    String line;

                    while((line = bufferedReader.readLine()) != null){
                        stringBuilder.append(line).append("\n");
                    }
                    stringBuilder.append("---\n");
                    bufferedReader.close();
                    urlConnection.disconnect();
                }

                return stringBuilder.toString();

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        protected void onPostExecute(String response){
            String[] responses;
            String delimiter = "\n---\n";
            responses = response.split(delimiter);
            json[0] = responses[0];
            json[1] = responses[1];
            json[2] = responses[2];
        }
    }
}


