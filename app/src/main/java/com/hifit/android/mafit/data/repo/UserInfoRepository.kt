package com.hifit.android.mafit.data.repo

import com.hifit.android.mafit.HiFitApplication
import com.hifit.android.mafit.data.model.login.LoginRequestBody
import com.hifit.android.mafit.data.model.survey.HealthInfoRequestBody
import com.hifit.android.mafit.data.source.local.UserInfo
import com.hifit.android.mafit.data.source.local.UserInfoDao
import com.hifit.android.mafit.data.source.network.HomeRetrofitInterface
import com.hifit.android.mafit.data.source.network.LoginRetrofitInterface
import com.hifit.android.mafit.data.source.network.SurveyRetrofitInterface
import com.hifit.android.mafit.util.Constant
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.create

class UserInfoRepository(private val userInfoDao: UserInfoDao, private val retrofit: Retrofit) {
    val userInfo: Flow<UserInfo> = userInfoDao.loadUserById()

    suspend fun insert(userInfo: UserInfo) {
        withContext(Dispatchers.IO) {
            userInfoDao.insertUserInfo(userInfo)
        }
    }

    suspend fun deleteUserInfo() {
        withContext(Dispatchers.IO) {
            userInfoDao.deleteAllUsers()
        }
    }

    suspend fun patchUserInfo(userInfo: HealthInfoRequestBody) =
        retrofit.create(SurveyRetrofitInterface::class.java).patchHealthInfo(userInfo)

    suspend fun patchStamps() =
        retrofit.create(HomeRetrofitInterface::class.java).patchStamps()

    suspend fun getUserInfo() =
        retrofit.create(SurveyRetrofitInterface::class.java).getHealthStatusInfo()

    suspend fun postLogin(loginRequestBody: LoginRequestBody) =
        retrofit.create(LoginRetrofitInterface::class.java).postLogin(loginRequestBody)

    suspend fun getExercises() = retrofit.create(HomeRetrofitInterface::class.java).getExercises()

    suspend fun getDiet() = retrofit.create(HomeRetrofitInterface::class.java).getDiet()

    suspend fun getBodyInfo() = retrofit.create(HomeRetrofitInterface::class.java).getBodyInfo()

    suspend fun getWorkoutInfo() =
        retrofit.create(HomeRetrofitInterface::class.java).getWorkoutInfo()

    suspend fun getWorkoutStatus() =
        retrofit.create(HomeRetrofitInterface::class.java).getWorkoutStatus()

    suspend fun postPoints() =
        retrofit.create(HomeRetrofitInterface::class.java).postPoints()

    fun storeToken(code: String) {
        HiFitApplication.sharedPreferences.edit().putString(
            Constant.X_ACCESS_TOKEN, "Bearer $code"
        ).apply()
    }

    fun deleteToken() {
        HiFitApplication.sharedPreferences.edit().clear().apply()
    }

    fun getToken() = HiFitApplication.sharedPreferences.getString(Constant.X_ACCESS_TOKEN, null)
}