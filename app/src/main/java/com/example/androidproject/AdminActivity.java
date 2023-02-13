package com.example.androidproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class AdminActivity extends AppCompatActivity {
    Button seeFlights, addFlights;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        TextView textView = findViewById(R.id.textView);
        seeFlights = findViewById(R.id.buttonAllFlights);
        addFlights = findViewById(R.id.buttonAddFlights);

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });

        seeFlights.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(AdminActivity.this, "All flights", Toast.LENGTH_SHORT).show();
            }
        });

        addFlights.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), AddFlightActivity.class);
                startActivity(intent);
            }
        });
    }
}