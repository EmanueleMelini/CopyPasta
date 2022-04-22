package it.emanuelemelini.copypasta.http

import android.content.Context
import com.google.gson.GsonBuilder
import it.emanuelemelini.copypasta.utils.ConfigHelper
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class APIClient {

    companion object {

        fun getClient(c: Context): Retrofit {

            val interceptor = HttpLoggingInterceptor()
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)

            val client: OkHttpClient = OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .build()

            val gson = GsonBuilder()
                .setLenient()
                .create()

            var url = ConfigHelper.getConfigValue(c, "internal_url")

            if (url.lowercase().contentEquals("error")) url = ""

            println("URL: $url")

            return Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(client)
                .build()

        }

    }

}