package com.hifit.android.mafit.data.source.network

import com.hifit.android.mafit.data.model.login.LoginRequestBody
import com.hifit.android.mafit.data.model.login.LoginResponse
import retrofit2.http.Body
import retrofit2.http.POST


interface LoginRetrofitInterface {
    @POST("/users/oauth/login")
    suspend fun postLogin(
        @Body body: LoginRequestBody,
    ): LoginResponse
}
