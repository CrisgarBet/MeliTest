package com.example.melitest.data.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class SalesModel(

    @SerializedName("period") var period: String? = null,
    @SerializedName("completed") var completed: Float? = null

) : Serializable
