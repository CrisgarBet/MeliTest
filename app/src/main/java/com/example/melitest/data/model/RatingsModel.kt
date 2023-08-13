package com.example.melitest.data.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class RatingsModel(
    @SerializedName("negative") var negative: Float? = null,
    @SerializedName("neutral") var neutral: Float? = null,
    @SerializedName("positive") var positive: Float? = null
) : Serializable
