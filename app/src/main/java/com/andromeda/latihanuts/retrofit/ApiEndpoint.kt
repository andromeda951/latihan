package com.andromeda.latihanuts.retrofit

import com.andromeda.latihanuts.MovieModel
import retrofit2.Call
import retrofit2.http.GET

interface ApiEndpoint {

    @GET("/")
    fun getMovie(): Call<List<MovieModel>>
}