package com.example.skillcinema.entity

import com.google.gson.annotations.SerializedName

data class ListByKeyword(
    @SerializedName("keyword") val keyword: String,
    @SerializedName("pagesCount") val pagesCount: Int,
    @SerializedName("films") val films: List<FilmsByKeyword>,
)

data class FilmsByKeyword(
    @SerializedName("filmId") val filmId: Int,
    @SerializedName("nameRu") val nameRu: String,
    @SerializedName("type") val type: String,
    @SerializedName("year") val year: String,
    @SerializedName("description") val description: String,
    @SerializedName("filmLength") val filmLength: String,
    @SerializedName("countries") val countries: List<CountriesByKeyword>,
    @SerializedName("genres") val genres: List<GenresByKeyword>,
    @SerializedName("rating") val rating: String,
    @SerializedName("ratingVoteCount") val ratingVoteCount: Int,
    @SerializedName("posterUrl") val posterUrl: String,
    @SerializedName("posterUrlPreview") val posterUrlPreview: String
)

data class CountriesByKeyword(
    @SerializedName("country") val filmLength: String
)

data class GenresByKeyword(
    @SerializedName("genre") val genre: String
)