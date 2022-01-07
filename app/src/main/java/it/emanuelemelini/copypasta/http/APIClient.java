package it.emanuelemelini.copypasta.http;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import it.emanuelemelini.copypasta.utils.ConfigHelper;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class APIClient {

    public static Retrofit getClient(Context c) {

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        String url = ConfigHelper.getConfigValue(c, "public_url");
        if(url == null)
            url = "";

        return new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(client)
                .build();
    }
}
