package com.example.androidproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;
import java.util.List;

public class UserActivity extends AppCompatActivity {
    RecyclerView mRecyclerView;
    myAdapter mAdapter;
    Button seeFlights, myFlights;
    String userName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        seeFlights = (Button) findViewById(R.id.buttonAllFlights);
        myFlights = (Button) findViewById(R.id.buttonMyFlights);
        TextView textView = findViewById(R.id.textView);

        Intent i = getIntent();
        userName = (String) i.getSerializableExtra("username");

        List<String> cities = Arrays.asList("Rome", "Manchester", "Brussels",
                "Ljubjana", "Venice", "London", "Miami");

        List<Integer> pictures = Arrays.asList(R.drawable.rome, R.drawable.manchester, R.drawable.brussels,
                R.drawable.ljubjana, R.drawable.venice, R.drawable.london, R.drawable.miami);

        mRecyclerView = (RecyclerView) findViewById(R.id.list);
        mRecyclerView.setHasFixedSize(true);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        mRecyclerView.setItemAnimator(new DefaultItemAnimator());

        mAdapter = new myAdapter(cities, pictures, userName, R.layout.my_row, this);

        mRecyclerView.setAdapter(mAdapter);

        seeFlights.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               Intent intent = new Intent(getApplicationContext(), FlightsActivity.class);
               intent.putExtra("allOrDest", "all");
               intent.putExtra("username", userName);
               startActivity(intent);
            }
        });

        myFlights.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), UserFlightsActivity.class);
                intent.putExtra("username", userName);
                startActivity(intent);
            }
        });

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });
    }
}