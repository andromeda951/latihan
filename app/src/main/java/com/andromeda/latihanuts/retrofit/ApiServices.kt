package com.andromeda.latihanuts.retrofit

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory


object ApiServices {

    val BASE_URL: String = "https://imdb-top-100-movies.p.rapidapi.com/"
    val endpoint: ApiEndpoint
        get() {

            val interceptor = HttpLoggingInterceptor()
            interceptor.level = HttpLoggingInterceptor.Level.BODY

            val client = OkHttpClient.Builder()
                .addInterceptor { chain ->
                    val request = chain.request().newBuilder()
                        .addHeader("X-RapidAPI-Key", "5698ef0109msh30013549e357d04p1a9d60jsn69d4c77eff4b")
                        .addHeader("X-RapidAPI-Host", "imdb-top-100-movies.p.rapidapi.com")
                        .build()
                    chain.proceed(request)
                }
                .addInterceptor(interceptor)
                .build()

            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(MoshiConverterFactory.create())
                .build()

            return retrofit.create(ApiEndpoint::class.java)
        }
}