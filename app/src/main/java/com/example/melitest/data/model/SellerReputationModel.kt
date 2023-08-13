package com.example.melitest.data.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class SellerReputationModel(
    @SerializedName("level_id") var levelId: String? = null,
    @SerializedName("power_seller_status") var powerSellerStatus: String? = null,
    @SerializedName("transactions") var transactions: TransactionsModel? = TransactionsModel(),
    @SerializedName("metrics") var metrics: MetricsModel? = MetricsModel()
) : Serializable
