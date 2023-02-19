package com.example.skillcinema.entity

import com.google.gson.annotations.SerializedName

data class MovieSerDramDet(
    @SerializedName("total") val total: Int,
    @SerializedName("totalPages") val totalPages: Int,
    @SerializedName("items") val items: List<FilmsSerDramDet>,
)

data class FilmsSerDramDet(
    @SerializedName("kinopoiskId") val kinopoiskId: Int,
    @SerializedName("imdbId") val imdbId: String,
    @SerializedName("nameRu") val nameRu: String,
    @SerializedName("nameEn") val nameEn: String?,
    @SerializedName("nameOriginal") val nameOriginal: String?,
    @SerializedName("countries") val countries: List<CountrySerDramDet>,
    @SerializedName("genres") val genres: List<GenresSerDramDet>,
    @SerializedName("ratingKinopoisk") val ratingKinopoisk: Double,
    @SerializedName("ratingImdb") val ratingImdb: Double?,
    @SerializedName("year") val year: Int,
    @SerializedName("type") val type: String,
    @SerializedName("posterUrl") val posterUrl: String,
    @SerializedName("posterUrlPreview") val posterUrlPreview: String
)

data class CountrySerDramDet(
    @SerializedName("country") val country: String,
)

data class GenresSerDramDet(
    @SerializedName("genre") val genre: String,
)