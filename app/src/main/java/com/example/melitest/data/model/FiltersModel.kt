package com.example.melitest.data.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class FiltersModel(
    @SerializedName("id") var id: String? = null,
    @SerializedName("name") var name: String? = null,
    @SerializedName("type") var type: String? = null,
    @SerializedName("values") var values: MutableList<ValuesModel>? = null
) : Serializable
