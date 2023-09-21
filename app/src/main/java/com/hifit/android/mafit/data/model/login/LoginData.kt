package com.hifit.android.mafit.data.model.login

import com.google.gson.annotations.SerializedName

data class LoginData(
    @SerializedName("code")
    val code: String
)