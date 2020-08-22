package io.github.dvegasa.cp2020rnd.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by Ed Khalturin @DVegasa
 */

object RetrofitGenerator {
    private const val BASE_URL = "http://89.223.127.81:3000/"

    private val client = OkHttpClient.Builder()
        .addInterceptor(HttpLoggingInterceptor().also {
            it.level = HttpLoggingInterceptor.Level.BODY
        })
        .build()

    private val builder = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(client)
        .addConverterFactory(GsonConverterFactory.create())

    private val retrofit = builder.build()

    fun <T> create(t: Class<T>): T {
        return retrofit.create(t)
    }
}