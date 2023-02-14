package com.example.androidproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;

public class UserFlightsActivity extends AppCompatActivity {
    RecyclerView mRecyclerView;
    FlightAdapter mAdapter;
    DBUserFlights2 DBFlights;
    ArrayList<String> destinations, prices, types, dates, flightIds;
    String allFlights;
    String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_flights);
        destinations = new ArrayList<>();
        prices = new ArrayList<>();
        types = new ArrayList<>();
        dates = new ArrayList<>();
        flightIds = new ArrayList<>();
        DBFlights = new DBUserFlights2(this);
        allFlights = "";

        Intent i = getIntent();

        username = (String) i.getSerializableExtra("username");


        storeDataInArrays();

        mRecyclerView = (RecyclerView) findViewById(R.id.userflights);
        mRecyclerView.setHasFixedSize(true);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        mRecyclerView.setItemAnimator(new DefaultItemAnimator());

        mAdapter = new FlightAdapter(destinations, dates, types, prices, username, flightIds, "Cancel",  R.layout.my_row2, this);

        mRecyclerView.setAdapter(mAdapter);
    }

    private void storeDataInArrays() {
        Cursor cursor = DBFlights.readAllDataByUsername(username);

        if(cursor.getCount() == 0) {
            Toast.makeText(this, "No data.", Toast.LENGTH_SHORT).show();
        } else {
            while(cursor.moveToNext()) {
                flightIds.add(cursor.getString(2));
                destinations.add(cursor.getString(3));
                types.add(cursor.getString(5));
                dates.add(cursor.getString(4));
                prices.add(cursor.getString(6));
            }
        }
    }
}