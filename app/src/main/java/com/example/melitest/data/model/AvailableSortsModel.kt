package com.example.melitest.data.model

import com.google.gson.annotations.SerializedName

data class AvailableSortsModel(
    @SerializedName("id") var id: String? = null,
    @SerializedName("name") var name: String? = null
)
