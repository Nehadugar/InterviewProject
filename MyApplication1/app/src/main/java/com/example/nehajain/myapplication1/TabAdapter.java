package com.example.nehajain.myapplication1;


import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import static android.media.CamcorderProfile.get;
import static com.example.nehajain.myapplication1.R.layout.activity_tab_adapter;
import static com.example.nehajain.myapplication1.R.layout.tooltip;
import static java.security.AccessController.getContext;

public class TabAdapter extends RecyclerView.Adapter<TabAdapter.MyViewHolder> {

    private List<Movies> moviesList;
    private static Context context;
    String genreStr;

    public TabAdapter(List<Movies> moviesList, Context context) {
        this.moviesList = moviesList;
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_tab_adapter, parent, false);
        return new MyViewHolder(v, context, moviesList);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        Movies movies = moviesList.get(position);
        holder.title.setText(movies.getTitle());
        Glide.with(context).load(movies.getImage()).into(holder.image);
        holder.rating.setText(movies.getRating());
        holder.releaseYear.setText(movies.getReleaseYear());
        genreStr = "";
        for (String str : movies.getGenre()) {
            genreStr += str + ", ";
        }
        genreStr = genreStr.length() > 0 ? genreStr.substring(0,
                genreStr.length() - 2) : genreStr;
        holder.genre.setText(genreStr);

        /*holder.genre.setText(movies.getGenre().get(0));*/


    }

    @Override
    public int getItemCount() {
        return moviesList.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        private SharedPreferences sp;
        private SharedPreferences.Editor editor;
        private TextView title, rating, releaseYear;
        private TextView genre;
        private ImageView image;
        private Context context;
        private Button button;
        private List<Movies> moviesLists = new ArrayList<Movies>();

        public MyViewHolder(View itemView, final Context context, final List<Movies> moviesLists) {
            super(itemView);
            TabAdapter.context = context;
            this.moviesLists = moviesList;
            title = itemView.findViewById(R.id.title);
            rating = itemView.findViewById(R.id.rating);
            releaseYear = itemView.findViewById(R.id.releaseYear);
            image = itemView.findViewById(R.id.image);
            genre = itemView.findViewById(R.id.genre);
            button = itemView.findViewById(R.id.showDetails);

            sp = context.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
            editor = sp.edit();

            button.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(final View view) {
                    final int position = getAdapterPosition();

                    final AlertDialog.Builder builder = new AlertDialog.Builder( TabAdapter.context );

                    builder.setMessage( "Are You Sure ?" ).setPositiveButton( "Confirm",
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    Movies movies = moviesLists.get(position);
                                    Tab2 tab2Fragemnt = new Tab2();

                                    Bundle bundle = new Bundle();
                                    bundle.putString("title", movies.getTitle());
                                    bundle.putString("image", movies.getImage());
                                    bundle.putString( "rating",movies.getRating() );
                                    bundle.putString( "releaseYear",movies.getReleaseYear() );
                                    bundle.putString( "genre",genreStr );
                                    tab2Fragemnt.setArguments(bundle);
                                    AppCompatActivity activity = (AppCompatActivity)view.getContext();
                                    activity.getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, tab2Fragemnt).addToBackStack(null).commit();
                                }
                            } ).setNegativeButton( "Cancel", null ).create().show();
                }
            } );
        }
    }
}