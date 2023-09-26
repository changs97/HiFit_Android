package com.hifit.android.mafit.data.model.survey

import com.google.gson.annotations.SerializedName

data class HealthStatusInfoData(
    @SerializedName("age")
    val age: Int?,
    @SerializedName("diastolicBloodPressure")
    val diastolicBloodPressure: Int,
    @SerializedName("gender")
    val gender: String?,
    @SerializedName("heartRate")
    val heartRate: Int,
    @SerializedName("height")
    val height: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("sarcopenia")
    val sarcopenia: String?,
    @SerializedName("systolicBloodPressure")
    val systolicBloodPressure: Int,
    @SerializedName("waistSize")
    val waistSize: Int,
    @SerializedName("weight")
    val weight: Int
)