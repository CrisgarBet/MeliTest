package com.example.melitest.data.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class InstallmentsModel(

    @SerializedName("quantity") var quantity: Float? = null,
    @SerializedName("amount") var amount: Float? = null,
    @SerializedName("rate") var rate: Float? = null,
    @SerializedName("currency_id") var currencyId: String? = null

) : Serializable
