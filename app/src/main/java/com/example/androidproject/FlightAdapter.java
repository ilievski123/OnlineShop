package com.example.androidproject;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
import java.util.UUID;

public class FlightAdapter extends RecyclerView.Adapter<FlightAdapter.ViewHolder> {

    private List<String> destinations;
    private List<String> date_flying;
    private List<String> way;
    private List<String> price, flightId;
    String userName, where;
    private int rowLayout;
    private Context mContext;

    // Референца на views за секој податок
    // Комплексни податоци може да бараат повеќе views per item
    // Пристап до сите views за податок се дефинира во view holder

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView Destination;
        public TextView Date;
        public TextView Way;
        public TextView Price;
        public Button Btn;
        public ImageView Pic;

        public ViewHolder(View itemView) {
            super(itemView);
            Destination = (TextView) itemView.findViewById(R.id.destination);
            Date = (TextView) itemView.findViewById(R.id.date);
            Way = (TextView) itemView.findViewById(R.id.type);
            Price = (TextView) itemView.findViewById(R.id.price);
            Btn = (Button) itemView.findViewById(R.id.btn);
            Pic = (ImageView) itemView.findViewById(R.id.picture_flight);
        }
    }

    // конструктор
    public FlightAdapter(List<String> destinations, List<String> date_flying,List<String> way,List<String> price, String userName, List<String> flightId, String where, int rowLayout, Context context) {
        this.destinations = destinations;
        this.date_flying = date_flying;
        this.way = way;
        this.price = price;
        this.userName = userName;
        this.flightId = flightId;
        this.where = where;
        this.rowLayout = rowLayout;
        this.mContext = context;
    }

    // Креирање нови views (повикано од layout manager)
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(rowLayout, viewGroup, false);
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel("My Notification", "My Notification", NotificationManager.IMPORTANCE_DEFAULT);
            NotificationManager manager = v.getContext().getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel);
        }
        return new ViewHolder(v);
    }

    // Замена на содржината во view (повикано од layout manager)
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        String flId = flightId.get(i);
        String dest = destinations.get(i);
        viewHolder.Destination.setText(dest);
        String df = date_flying.get(i);
        viewHolder.Date.setText(df);
        String w = way.get(i);
        viewHolder.Way.setText(w);
        String pr = price.get(i);
        viewHolder.Price.setText(pr);
        viewHolder.Pic.setImageResource(R.drawable.plane);

        if(where.equals("Buy")) {
            viewHolder.Btn.setText("Buy");
            viewHolder.Btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    NotificationCompat.Builder builder = new NotificationCompat.Builder(v.getContext(), "My Notification");
                    builder.setContentTitle("Success!");
                    builder.setContentText("You have successfully bought this flight ticket!");
                    builder.setSmallIcon(R.drawable.plane);
                    builder.setAutoCancel(true);

                    NotificationManagerCompat managerCompat = NotificationManagerCompat.from(v.getContext());
                    managerCompat.notify(1, builder.build());

                    UUID id = java.util.UUID.randomUUID();

                    DBUserFlights2 DB = new DBUserFlights2(v.getContext());
                    DB.insertData(id.toString(),userName, flId, dest, df, w, pr, "", "");

                    Intent intent = new Intent(v.getContext(), UserActivity.class);
                    intent.putExtra("username", userName);
                    v.getContext().startActivity(intent);
                }
            });
        } else if(where.equals("Cancel")) {
            viewHolder.Btn.setText("Cancel");
            viewHolder.Btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    NotificationCompat.Builder builder = new NotificationCompat.Builder(v.getContext(), "My Notification");
                    builder.setContentTitle("Success!");
                    builder.setContentText("You have successfully canceled your flight ticket!");
                    builder.setSmallIcon(R.drawable.plane);
                    builder.setAutoCancel(true);

                    NotificationManagerCompat managerCompat = NotificationManagerCompat.from(v.getContext());
                    managerCompat.notify(1, builder.build());

                    DBUserFlights2 DB = new DBUserFlights2(v.getContext());
                    DB.deleteDataByFlightId(flId);

                    Intent intent = new Intent(v.getContext(), UserActivity.class);
                    intent.putExtra("username", userName);
                    v.getContext().startActivity(intent);
                }
            });
        } else if(where.equals("See")) {
            viewHolder.Btn.setText("See");
            viewHolder.Btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(v.getContext(), FlightUsersActivity.class);
                    intent.putExtra("flightId", flId);
                    v.getContext().startActivity(intent);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return destinations == null ? 0 : destinations.size();
    }

}
