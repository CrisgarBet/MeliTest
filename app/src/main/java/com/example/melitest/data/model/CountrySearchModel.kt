package com.example.melitest.data.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class CountrySearchModel(
    @SerializedName("id") var id: String? = null,
    @SerializedName("name") var name: String? = null
) : Serializable
