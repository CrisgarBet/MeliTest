package com.example.melitest.data.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class SellerAddressModel(
    @SerializedName("comment") var comment: String? = null,
    @SerializedName("address_line") var addressLine: String? = null,
    @SerializedName("id") var id: String? = null,
    @SerializedName("latitude") var latitude: String? = null,
    @SerializedName("longitude") var longitude: String? = null,
    @SerializedName("country") var country: CountrySearchModel? = CountrySearchModel(),
    @SerializedName("state") var state: StateSearchModel? = StateSearchModel(),
    @SerializedName("city") var city: CitySearchModel? = CitySearchModel()
) : Serializable
