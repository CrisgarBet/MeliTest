package com.example.melitest.data.model

import com.google.gson.annotations.SerializedName

data class PagingModel(
    @SerializedName("total") var total: Long? = null,
    @SerializedName("primary_results") var primaryResults: Long? = null,
    @SerializedName("offset") var offset: Long? = null,
    @SerializedName("limit") var limit: Long? = null
)
