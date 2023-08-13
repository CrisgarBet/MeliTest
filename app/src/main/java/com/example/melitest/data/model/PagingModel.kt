package com.example.melitest.data.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class PagingModel(
    @SerializedName("total") var total: Float? = null,
    @SerializedName("primary_results") var primaryResults: Float? = null,
    @SerializedName("offset") var offset: Float? = null,
    @SerializedName("limit") var limit: Float? = null
) : Serializable
