package com.example.melitest.data.model

import com.google.gson.annotations.SerializedName

data class MetricsModel(

    @SerializedName("sales") var sales: SalesModel? = SalesModel(),
    @SerializedName("claims") var claims: ClaimsModel? = ClaimsModel(),
    @SerializedName("delayed_handling_time") var delayedHandlingTime: DelayedHandlingTimeModel? = DelayedHandlingTimeModel(),
    @SerializedName("cancellations") var cancellations: CancellationsModel? = CancellationsModel()

)
