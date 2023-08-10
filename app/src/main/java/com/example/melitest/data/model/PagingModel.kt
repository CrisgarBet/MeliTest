package com.example.melitest.data.model

import com.google.gson.annotations.SerializedName

data class PagingModel(
    @SerializedName("total") var total: Int? = null,
    @SerializedName("primary_results") var primaryResults: Int? = null,
    @SerializedName("offset") var offset: Int? = null,
    @SerializedName("limit") var limit: Int? = null
)
