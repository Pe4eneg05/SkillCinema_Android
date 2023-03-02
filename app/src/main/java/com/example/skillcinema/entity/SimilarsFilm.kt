package com.example.skillcinema.entity

import com.google.gson.annotations.SerializedName

data class SimilarFilms(
    @SerializedName("total") val total: Int,
    @SerializedName("items") val items: List<SimilarItems>
)

data class SimilarItems(
    @SerializedName("filmId") val filmId: Int,
    @SerializedName("nameRu") val nameRu: String,
    @SerializedName("nameEn") val nameEn: String,
    @SerializedName("nameOriginal") val nameOriginal: String,
    @SerializedName("posterUrl") val posterUrl: String,
    @SerializedName("posterUrlPreview") val posterUrlPreview: String,
    @SerializedName("relationType") val relationType: String
)
