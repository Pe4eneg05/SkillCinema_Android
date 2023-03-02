package com.example.skillcinema.entity

import com.google.gson.annotations.SerializedName

data class ImageList(
    @SerializedName("total") val total: Int,
    @SerializedName("totalPages") val totalPages: Int,
    @SerializedName("items") val items: List<PhotoItems>
)

data class PhotoItems(
    @SerializedName("imageUrl") val imageUrl: String,
    @SerializedName("previewUrl") val previewUrl: String
)
