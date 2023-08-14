package com.example.melitest.data.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class SellerModel(
    @SerializedName("id") var id: Float? = null,
    @SerializedName("nickname") var nickname: String? = null,
    @SerializedName("car_dealer") var carDealer: Boolean? = null,
    @SerializedName("real_estate_agency") var realEstateAgency: Boolean? = null,
    @SerializedName("_") var underscore: Boolean? = null,
    @SerializedName("registration_date") var registrationDate: String? = null,
    @SerializedName("tags") var tags: MutableList<String>? = null,
    @SerializedName("car_dealer_logo") var carDealerLogo: String? = null,
    @SerializedName("permalink") var permalink: String? = null,
    @SerializedName("seller_reputation") var sellerReputation: SellerReputationModel? = SellerReputationModel(),
    @SerializedName("eshop") var eshop: EshopModel? = EshopModel()
) : Serializable
