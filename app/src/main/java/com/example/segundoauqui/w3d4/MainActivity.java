package com.example.segundoauqui.w3d4;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.segundoauqui.w3d4.model.Example;
import com.example.segundoauqui.w3d4.model.Item;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    public static final String BASE_URL_TWO = "http://api.flickr.com/services/feeds/photos_public.gne?tag=kitten&format=json&nojsoncallback=1";
    private static final String TAG = "MainActivity";
    private RetrofitHelper retrofitHelper;
    RecyclerView rvReprofit;
    RecyclerView.LayoutManager layoutManager;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rvReprofit = (RecyclerView) findViewById(R.id.rvRetrofit);
        layoutManager = new LinearLayoutManager(getApplicationContext());
        rvReprofit = (RecyclerView) findViewById(R.id.rvRetrofit);
        layoutManager = new LinearLayoutManager(getApplicationContext());
        RecyclerView.ItemAnimator itemAnimator = new DefaultItemAnimator();
        rvReprofit.setLayoutManager(layoutManager);
        rvReprofit.setItemAnimator(itemAnimator);

        getRetrofit();
    }

    public void getRetrofit() {
        retrofit2.Call<Example> exampleDataCall = RetrofitHelper.getExampleCall();
        exampleDataCall.enqueue(new retrofit2.Callback<Example>() {
            @Override
            public void onFailure(Call<Example> call, Throwable t) {
            }

            @Override
            public void onResponse(Call<Example> call, final Response<Example> response) {
              //  Log.d(TAG, "onResponse: " + response.body().getItems());
                        ArrayList<Item> example = (ArrayList<Item>) response.body().getItems();
                        RetrofitAdapter randomsListAdapter = new RetrofitAdapter(example);
                        rvReprofit.setAdapter(randomsListAdapter);
                        randomsListAdapter.notifyDataSetChanged();
            }
        });

    }
}

