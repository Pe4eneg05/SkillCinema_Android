package com.example.skillcinema.api

import com.example.skillcinema.entity.MovieInfo
import com.example.skillcinema.entity.MoviePremier
import com.example.skillcinema.entity.MovieSerDramDet
import com.example.skillcinema.entity.MovieTopPopular
import com.example.skillcinema.recyclerview.idFilm
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query
import retrofit2.http.Url
import java.util.*

interface MovieListApi{

    @Headers("X-API-KEY: $api_key")
    @GET
    suspend fun movieInfo(@Url url: String): MovieInfo


    @Headers("X-API-KEY: $api_key")
    @GET("/api/v2.2/films/premieres")
    suspend fun premierListHome(@Query("year") year: Int, @Query("month") mont: String): MoviePremier


    @Headers("X-API-KEY: $api_key")
    @GET("/api/v2.2/films/top?type=TOP_100_POPULAR_FILMS")
    suspend fun popularList(@Query("page") page: Int): MovieTopPopular

    @Headers("X-API-KEY: $api_key")
    @GET("/api/v2.2/films/top?type=TOP_100_POPULAR_FILMS")
    suspend fun popularListHome(): MovieTopPopular


    @Headers("X-API-KEY: $api_key")
    @GET("/api/v2.2/films/?countries=34&genres=5")
    suspend fun detectivesList(@Query("page") page: Int): MovieSerDramDet

    @Headers("X-API-KEY: $api_key")
    @GET("/api/v2.2/films/?countries=34&genres=5")
    suspend fun detectivesListHome(): MovieSerDramDet


    @Headers("X-API-KEY: $api_key")
    @GET("/api/v2.2/films/top?type=TOP_250_BEST_FILMS")
    suspend fun topList(@Query("page") page: Int): MovieTopPopular

    @Headers("X-API-KEY: $api_key")
    @GET("/api/v2.2/films/top?type=TOP_250_BEST_FILMS")
    suspend fun topListHome(): MovieTopPopular


    @Headers("X-API-KEY: $api_key")
    @GET("/api/v2.2/films/?countries=33&genres=2")
    suspend fun dramList(@Query("page") page: Int): MovieSerDramDet

    @Headers("X-API-KEY: $api_key")
    @GET("/api/v2.2/films/?countries=33&genres=2")
    suspend fun dramListHome(): MovieSerDramDet


    @Headers("X-API-KEY: $api_key")
    @GET("/api/v2.2/films/?type=TV_SERIES")
    suspend fun serList(@Query("page") page: Int): MovieSerDramDet

    @Headers("X-API-KEY: $api_key")
    @GET("/api/v2.2/films/?type=TV_SERIES")
    suspend fun serListHome(): MovieSerDramDet

    private companion object {
        private const val api_key2 = "ff463c7e-2bf1-4303-ad72-527a3a550c7c"
        private const val api_key1 = "de25422e-3d1e-4665-acc4-0daaf93901e1"
        private const val api_key = "5d114bea-b474-49c1-b148-95f870c4f526"
    }
}

val retrofit = Retrofit
    .Builder()
    .baseUrl("https://kinopoiskapiunofficial.tech")
    .addConverterFactory(GsonConverterFactory.create())
    .build()
    .create(MovieListApi::class.java)