package com.example.skillcinema.entity

import com.google.gson.annotations.SerializedName

data class StaffInfo(
    @SerializedName("personId") val personId: Int,
    @SerializedName("webUrl") val webUrl: String,
    @SerializedName("nameRu") val nameRu: String?,
    @SerializedName("nameEn") val nameEn: String?,
    @SerializedName("posterUrl") val posterUrl: String,
    @SerializedName("profession") val profession: String?,
    @SerializedName("films") val films: List<FilmsStaff>
)

data class FilmsStaff(
    @SerializedName("filmId") val filmId: Int,
    @SerializedName("nameRu") val nameRu: String,
    @SerializedName("nameEn") val nameEn: String,
    @SerializedName("rating") val rating: String?,
    @SerializedName("general") val general: Boolean,
    @SerializedName("description") val description: String,
    @SerializedName("profession") val profession: String
)