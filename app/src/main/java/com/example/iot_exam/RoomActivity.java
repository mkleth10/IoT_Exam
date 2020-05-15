package com.example.iot_exam;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class RoomActivity extends AppCompatActivity {
    Button temp_button, hum_button, lpg_button, co_button, smoke_button;
    String json;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.room_layout);

        temp_button = findViewById(R.id.temp);
        hum_button = findViewById(R.id.humidity);
        lpg_button = findViewById(R.id.lpg);
        co_button = findViewById(R.id.co);
        smoke_button = findViewById(R.id.smoke);

        json = getIntent().getStringExtra("json");

        temp_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(RoomActivity.this, TempActivity.class);
                myIntent.putExtra("json", json);
                startActivity(myIntent);
            }
        });

        hum_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(RoomActivity.this, HumActivity.class);
                myIntent.putExtra("json", json);
                startActivity(myIntent);
            }
        });

        lpg_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(RoomActivity.this, LpgActivity.class);
                myIntent.putExtra("json", json);
                startActivity(myIntent);
            }
        });

        co_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(RoomActivity.this, CoActivity.class);
                myIntent.putExtra("json", json);
                startActivity(myIntent);
            }
        });

        smoke_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(RoomActivity.this, TempActivity.class);
                myIntent.putExtra("json", json);
                startActivity(myIntent);
            }
        });
    }
}
