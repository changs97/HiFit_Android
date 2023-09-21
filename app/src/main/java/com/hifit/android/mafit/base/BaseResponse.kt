package com.hifit.android.mafit.base

import com.google.gson.annotations.SerializedName

open class BaseResponse(
    @SerializedName("code")
    val code: Int = 0,
    @SerializedName("message")
    val message: String = "",
    @SerializedName("status")
    val status: String = ""
)