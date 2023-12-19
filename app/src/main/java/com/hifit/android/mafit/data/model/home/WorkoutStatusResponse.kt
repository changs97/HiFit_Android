package com.hifit.android.mafit.data.model.home

import com.google.gson.annotations.SerializedName
import com.hifit.android.mafit.base.BaseResponse

data class WorkoutStatusResponse(
    @SerializedName("data")
    val data: Boolean
) : BaseResponse()
