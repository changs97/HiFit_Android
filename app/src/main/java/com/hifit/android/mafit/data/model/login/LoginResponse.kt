package com.hifit.android.mafit.data.model.login

import com.google.gson.annotations.SerializedName
import com.hifit.android.mafit.base.BaseResponse

data class LoginResponse(
    @SerializedName("data")
    val data: LoginData,
): BaseResponse()