package com.example.melitest.data.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class ValuesModel(
    @SerializedName("id") var id: String? = null,
    @SerializedName("name") var name: String? = null,
    @SerializedName("results") var results: Float? = null
) : Serializable
