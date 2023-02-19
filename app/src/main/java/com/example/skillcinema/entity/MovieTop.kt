package com.example.skillcinema.entity

import com.google.gson.annotations.SerializedName

data class MovieTopPopular(
    @SerializedName("pagesCount") val pagesCount: Int,
    @SerializedName("films") val films: List<FilmsTopPopular>,
)

data class FilmsTopPopular(
    @SerializedName("filmId") val filmId: Int,
    @SerializedName("nameRu") val nameRu: String,
    @SerializedName("nameEn") val nameEn: String,
    @SerializedName("year") val year: String,
    @SerializedName("filmLength") val filmLength: String,
    @SerializedName("countries") val countries: List<CountryTopPopular>,
    @SerializedName("genres") val genres: List<GenresTopPopular>,
    @SerializedName("rating") val rating: String,
    @SerializedName("ratingVoteCount") val ratingVoteCount: Int,
    @SerializedName("posterUrl") val posterUrl: String,
    @SerializedName("posterUrlPreview") val posterUrlPreview: String,
    @SerializedName("ratingChange") val ratingChange: String?
)

data class CountryTopPopular(
    @SerializedName("country") val country: String,
)

data class GenresTopPopular(
    @SerializedName("genre") val genre: String,
)