package com.example.melitest.data.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class TransactionsModel(

    @SerializedName("canceled") var canceled: Float? = null,
    @SerializedName("completed") var completed: Float? = null,
    @SerializedName("period") var period: String? = null,
    @SerializedName("ratings") var ratings: RatingsModel? = RatingsModel(),
    @SerializedName("total") var total: Float? = null
) : Serializable
