package com.example.nehajain.myapplication1;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import okhttp3.Headers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Tab1 extends Fragment {
    private RecyclerView listRecycle;
    ProgressDialog progressDialog;
    private TabAdapter adapter;
    private List<Movies> moviesList = new ArrayList<>();
    private ApiInterface apiInterface;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_blank, container, false);

        listRecycle = view.findViewById(R.id.listRecycle);
        listRecycle.setHasFixedSize(true);
        listRecycle.setLayoutManager(new LinearLayoutManager(getActivity()));

        // Progress dialog
        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Loading....");
        progressDialog.show();

        apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        Call<List<Movies>> call = apiInterface.getMoviesData();


        call.enqueue(new Callback<List<Movies>>() {

            @Override
            public void onResponse(Call<List<Movies>> call, Response<List<Movies>> response) {
                hidePDialog();
                try {
                    if (response.isSuccessful()) {
//                    if (response != null && response.body() != null) {
                        Toast.makeText(getActivity(), "Success", Toast.LENGTH_LONG).show();
                        moviesList.clear();
                        moviesList.addAll(response.body());
                        moviesList = response.body();
                        adapter = new TabAdapter(moviesList, getActivity());
                        listRecycle.setAdapter(adapter);

                        Log.d("neha responseeee", response.toString());
//                        List<Movies> response1 = response.body();
//                        Log.d("Tag is ====== ", response1.get(0).getTitle() + " tag ");
                    } else {
                        Log.d("nehawwww responseeee", response.toString());
                        response.errorBody();

                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<List<Movies>> call, Throwable t) {
                Toast.makeText(getActivity().getApplicationContext(), "error message", Toast.LENGTH_LONG).show();
            }
        });
        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        hidePDialog();
    }

    private void hidePDialog() {
        if (progressDialog != null) {
            progressDialog.dismiss();
            progressDialog = null;
        }
    }

}


