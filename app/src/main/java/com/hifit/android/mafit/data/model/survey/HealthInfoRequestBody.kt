package com.hifit.android.mafit.data.model.survey

import com.google.gson.annotations.SerializedName

data class HealthInfoRequestBody(
    @SerializedName("diastolicBloodPressure")
    var diastolicBloodPressure: Int = 0,
    @SerializedName("heartRate")
    var heartRate: Int = 0,
    @SerializedName("height")
    var height: Int = 0,
    @SerializedName("systolicBloodPressure")
    var systolicBloodPressure: Int = 0,
    @SerializedName("waistSize")
    var waistSize: Int = 0,
    @SerializedName("weight")
    var weight: Int = 0
)