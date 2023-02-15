package com.example.androidproject;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class UsersTicketsAdapter extends RecyclerView.Adapter<UsersTicketsAdapter.ViewHolder> {

    private List<String> users;
    private List<String> longitude;
    private List<String> latitude;
    private int rowLayout;
    private Context mContext;

    // Референца на views за секој податок
    // Комплексни податоци може да бараат повеќе views per item
    // Пристап до сите views за податок се дефинира во view holder

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView User;
        public Button Btn;

        public ViewHolder(View itemView) {
            super(itemView);
            User = (TextView) itemView.findViewById(R.id.user);
            Btn = (Button) itemView.findViewById(R.id.btnMap);
        }
    }

    // конструктор
    public UsersTicketsAdapter(List<String> users, List<String> longitude, List<String> latitude, int rowLayout, Context context) {
        this.users = users;
        this.longitude = longitude;
        this.latitude = latitude;
        this.rowLayout = rowLayout;
        this.mContext = context;
    }

    // Креирање нови views (повикано од layout manager)
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(rowLayout, viewGroup, false);
        return new ViewHolder(v);
    }

    // Замена на содржината во view (повикано од layout manager)
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        String usr = users.get(i);
        Log.d("BABAA", usr);
        String lng = longitude.get(i);
        String lat = latitude.get(i);
        viewHolder.User.setText(usr);
        viewHolder.Btn.setText("See map");
        viewHolder.Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), AdminActivity.class);
                intent.putExtra("allOrDest", "dest");
                v.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return users == null ? 0 : users.size();
    }

}

