package com.example.melitest.data.model

import com.google.gson.annotations.SerializedName

data class RatingsModel(
    @SerializedName("negative") var negative: Double? = null,
    @SerializedName("neutral") var neutral: Double? = null,
    @SerializedName("positive") var positive: Double? = null
)
