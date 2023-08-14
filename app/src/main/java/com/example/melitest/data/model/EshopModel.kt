package com.example.melitest.data.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class EshopModel(

    @SerializedName("eshop_id") var eshopId: Float? = null,
    @SerializedName("seller") var seller: Float? = null,
    @SerializedName("nick_name") var nickName: String? = null,
    @SerializedName("eshop_status_id") var eshopStatusId: Float? = null,
    @SerializedName("site_id") var siteId: String? = null,
    @SerializedName("eshop_experience") var eshopExperience: Float? = null,
    @SerializedName("eshop_rubro") var eshopRubro: EshopRubroModel? = EshopRubroModel(),
    @SerializedName("eshop_locations") var eshopLocations: MutableList<EshopLocationsModel>? = null,
    @SerializedName("eshop_logo_url") var eshopLogoUrl: String? = null
) : Serializable
