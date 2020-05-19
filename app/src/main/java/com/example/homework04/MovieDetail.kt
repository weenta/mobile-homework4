package com.example.homework04

import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.Call


interface MovieDetail {
    @GET("movie/550?api_key=973086a14eaeb40eacd4912ffbbf3f59")
    fun getMovies(): Call<MovieInfos>
}