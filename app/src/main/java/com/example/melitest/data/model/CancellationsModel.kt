package com.example.melitest.data.model

import com.google.gson.annotations.SerializedName

data class CancellationsModel(
    @SerializedName("period") var period: String? = null,
    @SerializedName("rate") var rate: Float? = null,
    @SerializedName("value") var value: Float? = null
)
