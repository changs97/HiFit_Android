package com.hifit.android.mafit.data.model.survey

import com.google.gson.annotations.SerializedName
import com.hifit.android.mafit.base.BaseResponse

data class HealthStatusInfoResponse(
    @SerializedName("data")
    val data: HealthStatusInfoData
): BaseResponse()