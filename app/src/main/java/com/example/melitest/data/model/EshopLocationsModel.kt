package com.example.melitest.data.model


import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class EshopLocationsModel(

    @SerializedName("city") var city: CityModel? = CityModel(),
    @SerializedName("country") var country: CountryModel? = CountryModel(),
    @SerializedName("neighborhood") var neighborhood: NeighborhoodModel? = NeighborhoodModel(),
    @SerializedName("state") var state: StateModel? = StateModel()

): Serializable
