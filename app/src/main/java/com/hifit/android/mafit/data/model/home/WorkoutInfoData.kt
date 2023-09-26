package com.hifit.android.mafit.data.model.home

import com.google.gson.annotations.SerializedName

data class WorkoutInfoData(
    @SerializedName("month")
    val month: Int?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("point")
    val point: Int?,
    @SerializedName("stamp")
    val stamp: Int?,
    @SerializedName("targetStamp")
    val targetStamp: Int?
)