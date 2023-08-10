package com.example.melitest.data.model

import com.google.gson.annotations.SerializedName

data class FiltersModel(
    @SerializedName("id") var id: String? = null,
    @SerializedName("name") var name: String? = null,
    @SerializedName("type") var type: String? = null,
    @SerializedName("values") var values: ArrayList<ValuesModel> = arrayListOf()
)
