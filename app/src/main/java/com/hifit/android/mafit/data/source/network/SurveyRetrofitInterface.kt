package com.hifit.android.mafit.data.source.network

import com.hifit.android.mafit.data.model.survey.HealthInfoRequestBody
import com.hifit.android.mafit.data.model.survey.HealthInfoResponse
import com.hifit.android.mafit.data.model.survey.HealthStatusInfoResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PATCH


interface SurveyRetrofitInterface {
    @PATCH("/users/healthInfo")
    suspend fun patchHealthInfo(
        @Body body: HealthInfoRequestBody,
    ): Response<HealthInfoResponse>

    @GET("/users/healthStatusInfo")
    suspend fun getHealthStatusInfo(
    ): Response<HealthStatusInfoResponse>
}
