package com.example.melitest.data.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class AddressModel(

    @SerializedName("state_id") var stateId: String? = null,
    @SerializedName("state_name") var stateName: String? = null,
    @SerializedName("city_id") var cityId: String? = null,
    @SerializedName("city_name") var cityName: String? = null

) : Serializable
