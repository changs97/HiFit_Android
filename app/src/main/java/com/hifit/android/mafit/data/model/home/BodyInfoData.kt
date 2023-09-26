package com.hifit.android.mafit.data.model.home

import com.google.gson.annotations.SerializedName

data class BodyInfoData(
    @SerializedName("currentBmi")
    val currentBmi: Double?,
    @SerializedName("currentWeight")
    val currentWeight:Double?,
    @SerializedName("targetBmi")
    val targetBmi: Double?,
    @SerializedName("targetWeight")
    val targetWeight: Double?
)