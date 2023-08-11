package com.example.melitest.data.model

import com.google.gson.annotations.SerializedName

data class TransactionsModel(

    @SerializedName("canceled") var canceled: Long? = null,
    @SerializedName("completed") var completed: Long? = null,
    @SerializedName("period") var period: String? = null,
    @SerializedName("ratings") var ratings: RatingsModel? = RatingsModel(),
    @SerializedName("total") var total: Long? = null
)
