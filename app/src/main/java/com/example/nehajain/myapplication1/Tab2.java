package com.example.nehajain.myapplication1;


import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class Tab2 extends Fragment {
    private SharedPreferences sp;
    private SharedPreferences.Editor editor;
    private TextView title_view, rating_view, releaseYear_view, genre_view;
    private ImageView image_view;
    ProgressDialog progressDialog;
    private String title1, image1, rating1, release_year1, genre1;
    private Context context;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tab2, container, false);

        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setCancelable(false);

        sp = getActivity().getSharedPreferences("MyPrefs",Context.MODE_PRIVATE);
        editor = sp.edit();

        title1 = sp.getString("title", "");
        image1 = sp.getString("image", "");
        rating1 = sp.getString("rating", "");
        release_year1 = sp.getString("releaseYear", "");
        genre1 = sp.getString("genre", "");


        title_view = view.findViewById(R.id.title_view);
        rating_view = view.findViewById(R.id.rating_view);
        releaseYear_view = view.findViewById(R.id.releaseYear_view);
        genre_view = view.findViewById(R.id.genre_view);
        image_view = view.findViewById(R.id.image_view);

        if (title1 != null) {
            title_view.setText(title1);
        }
        if (rating1 != null) {
            rating_view.setText(rating1);
        }
        if (release_year1 != null) {
            releaseYear_view.setText(release_year1);
        }
        if (genre1 != null) {
            genre_view.setText(genre1);
        }
        if (!image1.equals("") && image1 != null) {
            Picasso.get()
                    .load(image1)
                    //.placeholder(R.drawable.user_dummy_male)
                    .into(image_view);
        }
            return view;
    }

}