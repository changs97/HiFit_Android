package com.hifit.android.mafit.data.model.home

import com.google.gson.annotations.SerializedName

data class DietData(
    @SerializedName("calorie")
    val calorie: Int?,
    @SerializedName("chickenBreastCount")
    val chickenBreastCount: Int?,
    @SerializedName("eggCount")
    val eggCount: Int?,
    @SerializedName("protein")
    val protein: Int?,
    @SerializedName("tofuCount")
    val tofuCount: Int?
)