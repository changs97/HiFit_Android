package com.hifit.android.mafit.data.model.login

import com.google.gson.annotations.SerializedName

data class LoginRequestBody(
    @SerializedName("code")
    val code: String
)