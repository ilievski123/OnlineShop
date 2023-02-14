package com.example.androidproject;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class myAdapter extends RecyclerView.Adapter<myAdapter.ViewHolder> {

    private List<String> myList;
    private List<Integer> myPictures;
    private int rowLayout;
    private Context mContext;
    private String username;

    // Референца на views за секој податок
    // Комплексни податоци може да бараат повеќе views per item
    // Пристап до сите views за податок се дефинира во view holder

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView myName;
        public ImageView Pic;

        public ViewHolder(View itemView) {
            super(itemView);
            myName = (TextView) itemView.findViewById(R.id.Name);
            Pic = (ImageView) itemView.findViewById(R.id.picture);
        }
    }

    // конструктор
    public myAdapter(List<String> myList, List<Integer> myPictures, String username, int rowLayout, Context context) {
        this.myList = myList;
        this.myPictures = myPictures;
        this.username = username;
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
        String entry = myList.get(i);
        Integer picture = myPictures.get(i);
        viewHolder.myName.setText(entry);
        viewHolder.Pic.setImageResource(picture);
        viewHolder.myName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), FlightsActivity.class);
                intent.putExtra("allOrDest", "dest");
                intent.putExtra("destination", entry);
                intent.putExtra("username", username);
                v.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return myList == null ? 0 : myList.size();
    }

}

