package com.example.melitest.data.model


import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class NeighborhoodModel(
    @SerializedName("id") var id: String? = null

): Serializable