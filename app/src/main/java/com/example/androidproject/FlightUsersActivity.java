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

public class FlightUsersActivity extends AppCompatActivity {
    RecyclerView mRecyclerView;
    UsersTicketsAdapter mAdapter;
    String flightId;
    DBUserFlights2 DBFlights;
    ArrayList<String> users, longitude, latitude;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flight_users);

        DBFlights = new DBUserFlights2(this);
        users = new ArrayList<>();
        longitude = new ArrayList<>();
        latitude = new ArrayList<>();

        Intent i = getIntent();
        flightId = (String) i.getSerializableExtra("flightId");

        storeDataInArrays();

        mRecyclerView = (RecyclerView) findViewById(R.id.flightsUsers);
        mRecyclerView.setHasFixedSize(true);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        mRecyclerView.setItemAnimator(new DefaultItemAnimator());

        mAdapter = new UsersTicketsAdapter(users, longitude, latitude, R.layout.my_row3, this);

        mRecyclerView.setAdapter(mAdapter);
    }

    private void storeDataInArrays() {
        Cursor cursor = DBFlights.readAllDataByFlightId(flightId);

        if(cursor.getCount() == 0) {
            Toast.makeText(this, "No data.", Toast.LENGTH_SHORT).show();
        } else {
            while(cursor.moveToNext()) {
                users.add(cursor.getString(1));
                longitude.add(cursor.getString(7));
                latitude.add(cursor.getString(8));
            }
        }
    }
}