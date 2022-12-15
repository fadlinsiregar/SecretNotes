package com.example.secretnotes.services

import com.example.secretnotes.helpers.Config
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object ServiceBuilder {
    private val logger: HttpLoggingInterceptor =
        HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

    private val okHttp: OkHttpClient.Builder = OkHttpClient.Builder()
        .callTimeout(4, TimeUnit.SECONDS)
        .addInterceptor(logger)

    // retrofit
    private val builder: Retrofit.Builder =
        Retrofit.Builder().baseUrl(Config.NOTES_API_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttp.build())

    // instance of retrofit
    private val retrofit: Retrofit = builder.build()

    fun <T> buildService(serviceType: Class<T>): T {
        return retrofit.create(serviceType)
    }
}