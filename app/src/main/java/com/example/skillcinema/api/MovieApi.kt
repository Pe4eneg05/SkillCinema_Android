package com.example.skillcinema.api

import com.example.skillcinema.entity.*
import com.example.skillcinema.recyclerview.idFilm
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query
import retrofit2.http.Url
import java.util.*

interface MovieListApi{

    //Поиск по ключевым словам
    @Headers("X-API-KEY: $api_key")
    @GET("/api/v2.1/films/search-by-keyword")
    suspend fun filmsByKeywordList(@Query("page") page: Int, @Query("keyword") keyword: String): ListByKeyword


    //Информация о человеке по ID
    @Headers("X-API-KEY: $api_key")
    @GET
    suspend fun staffInfo(@Url url: String): StaffInfo


    //Список похожих фильмов
    @Headers("X-API-KEY: $api_key")
    @GET
    suspend fun similarList(@Url url: String): SimilarFilms


    //Список с фотографиями фильма
    @Headers("X-API-KEY: $api_key")
    @GET
    suspend fun imageList(@Url url: String): ImageList


    //Список с актерами и режиссерским составом
    @Headers("X-API-KEY: $api_key")
    @GET("/api/v1/staff?")
    suspend fun actorsList(@Query("filmId") filmId: Int): List<ActorsList>


    //Информация о фильме по ID
    @Headers("X-API-KEY: $api_key")
    @GET
    suspend fun movieInfo(@Url url: String): MovieInfo


    @Headers("X-API-KEY: $api_key")
    @GET("/api/v2.2/films")
    suspend fun allFilmsList(@Query("page") page: Int): FilmList


    //Списки фильмов разных категорий
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
        private const val api_key = "ff463c7e-2bf1-4303-ad72-527a3a550c7c"
        private const val api_key1 = "de25422e-3d1e-4665-acc4-0daaf93901e1"
        private const val api_key2 = "5d114bea-b474-49c1-b148-95f870c4f526"
        private const val api_key3 = "8a4923cb-8898-4e13-9177-dc2516ef3180"
        private const val api_key4 = "0e21aff8-5635-4961-8fc9-648b7b60da87"
        private const val api_key5 = "be173999-6f7f-485f-b818-7b19dc0ddfec"
        private const val api_key6 = "5a5396d1-e402-4754-a62e-6a7f82f9f00e"
    }
}

val retrofit = Retrofit
    .Builder()
    .baseUrl("https://kinopoiskapiunofficial.tech")
    .addConverterFactory(GsonConverterFactory.create())
    .build()
    .create(MovieListApi::class.java)