package com.example.skillcinema.entity

import com.google.gson.annotations.SerializedName

data class MoviePremier(
    @SerializedName("total") val total: Int,
    @SerializedName("items") val items: List<FilmsPremier>,
)

data class FilmsPremier(
    @SerializedName("kinopoiskId") val kinopoiskId: Int,
    @SerializedName("nameRu") val nameRu: String,
    @SerializedName("nameEn") val nameEn: String?,
    @SerializedName("year") val year: Int,
    @SerializedName("posterUrl") val posterUrl: String,
    @SerializedName("posterUrlPreview") val posterUrlPreview: String,
    @SerializedName("countries") val countries: List<CountryPremier>,
    @SerializedName("genres") val genres: List<GenresPremier>,
    @SerializedName("duration") val duration: Int,
    @SerializedName("premiereRu") val premiereRu: String
)

data class CountryPremier(
    @SerializedName("country") val country: String,
)

data class GenresPremier(
    @SerializedName("genre") val genre: String,
)