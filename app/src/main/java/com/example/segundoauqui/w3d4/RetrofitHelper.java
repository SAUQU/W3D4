package com.example.segundoauqui.w3d4;

import com.example.segundoauqui.w3d4.model.Example;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Retrofit;

import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by segundoauqui on 8/18/17.
 */

public class RetrofitHelper {

    public static final String BASE_URL_TWO = "http://api.flickr.com/";
    public static final String PATH = "services/feeds/photos_public.gne";
    public static final String QUERY_TAG = "kitten";
    public static final String QUERY_FORMAT = "json";
    public static final String QUERY_CALL = "1";

    public static Retrofit create() {
        //creata a logging interceptor
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);

        // create a custom client to add the interceptor
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(logging)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .client(client)
                .baseUrl(BASE_URL_TWO)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        return retrofit;
    }


    public static Call<Example> getExampleCall() {
        Retrofit retrofit = create();
        RetrofitService retrofitService = retrofit.create(RetrofitService.class);
        return retrofitService.getRetrofitServiceData(QUERY_TAG,QUERY_FORMAT,QUERY_CALL);
    }

    public interface RetrofitService{

        @GET(PATH)
        Call<Example> getRetrofitServiceData(@Query("tag") String tag, @Query("format") String format, @Query("nojsoncallback") String nojsoncallback);
    }


}
