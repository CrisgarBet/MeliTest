package com.example.melitest.data.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class ValueStructModel(

    @SerializedName("number") var number: Float? = null,
    @SerializedName("unit") var unit: String? = null

) : Serializable
