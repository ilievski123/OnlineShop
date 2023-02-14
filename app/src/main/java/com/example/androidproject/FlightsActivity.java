package com.example.androidproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ContentResolver;
import android.content.Intent;
import android.database.CharArrayBuffer;
import android.database.ContentObserver;
import android.database.Cursor;
import android.database.DataSetObserver;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Objects;

public class FlightsActivity extends AppCompatActivity {
    RecyclerView mRecyclerView;
    FlightAdapter mAdapter;
    DBFlights DBFlights;
    ArrayList<String> destinations, prices, types, dates, flightIds;
    String allFlights;
    String dest, username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flights);

        destinations = new ArrayList<>();
        prices = new ArrayList<>();
        types = new ArrayList<>();
        dates = new ArrayList<>();
        flightIds = new ArrayList<>();
        DBFlights = new DBFlights(this);
        allFlights = "";

        Intent i = getIntent();
        allFlights = (String) i.getSerializableExtra("allOrDest");

        username = (String) i.getSerializableExtra("username");

        if(allFlights.equals("dest")) {
            dest = (String) i.getSerializableExtra("destination");
        }
        
        storeDataInArrays();

        mRecyclerView = (RecyclerView) findViewById(R.id.flights);
        mRecyclerView.setHasFixedSize(true);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        mRecyclerView.setItemAnimator(new DefaultItemAnimator());

        mAdapter = new FlightAdapter(destinations, dates, types, prices, username, flightIds, "Buy",  R.layout.my_row2, this);

        mRecyclerView.setAdapter(mAdapter);
    }

    private void storeDataInArrays() {
        Cursor cursor;
        if(allFlights.equals("all")) {
            cursor = DBFlights.readAllData();
        } else {
            cursor = DBFlights.readAllDataByDestination(dest);
        }

        if(cursor.getCount() == 0) {
            Toast.makeText(this, "No data.", Toast.LENGTH_SHORT).show();
        } else {
            while(cursor.moveToNext()) {
                flightIds.add(cursor.getString(0));
                destinations.add(cursor.getString(1));
                types.add(cursor.getString(3));
                dates.add(cursor.getString(2));
                prices.add(cursor.getString(4));
            }
        }
    }
}