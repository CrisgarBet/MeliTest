package com.example.melitest.data.model

import com.google.gson.annotations.SerializedName

data class InstallmentsModel(

    @SerializedName("quantity") var quantity: Long? = null,
    @SerializedName("amount") var amount: Double? = null,
    @SerializedName("rate") var rate: Long? = null,
    @SerializedName("currency_id") var currencyId: String? = null

)
