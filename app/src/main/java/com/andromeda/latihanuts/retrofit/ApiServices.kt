package com.andromeda.latihanuts.retrofit

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object ApiServices {

    val BASE_URL: String = "https://imdb-top-100-movies.p.rapidapi.com/"
    val endpoint: ApiEndpoint
        get() {

            val interceptor = HttpLoggingInterceptor()
            interceptor.level = HttpLoggingInterceptor.Level.BODY

            val client = OkHttpClient.Builder()
                .addInterceptor { chain ->
                    val request = chain.request().newBuilder()
                        .addHeader("X-RapidAPI-Key", "28a061dd22mshcc281775985f365p14a9a5jsnfaad84ca84bf")
                        .addHeader("X-RapidAPI-Host", "imdb-top-100-movies.p.rapidapi.com")
                        .build()
                    chain.proceed(request)
                }
                .addInterceptor(interceptor)
                .build()

            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            return retrofit.create(ApiEndpoint::class.java)
        }
}