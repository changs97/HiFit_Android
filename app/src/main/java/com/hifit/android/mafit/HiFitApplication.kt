package com.hifit.android.mafit

import android.app.Application
import android.content.SharedPreferences
import com.hifit.android.mafit.data.repo.UserInfoRepository
import com.hifit.android.mafit.data.source.local.AppDatabase
import com.hifit.android.mafit.util.Constant.Companion.BASE_URL
import com.hifit.android.mafit.util.Constant.Companion.X_ACCESS_TOKEN
import com.kakao.sdk.common.KakaoSdk
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import timber.log.Timber
import java.util.concurrent.TimeUnit

class HiFitApplication : Application() {
    companion object {
        lateinit var sharedPreferences: SharedPreferences
        lateinit var repository: UserInfoRepository
    }

    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }

        sharedPreferences = applicationContext.getSharedPreferences("MARFIT_APP", MODE_PRIVATE)

        val logger = HttpLoggingInterceptor().apply {
            level = if (BuildConfig.DEBUG) {
                HttpLoggingInterceptor.Level.BODY
            } else {
                HttpLoggingInterceptor.Level.NONE
            }
        }

        val requestInterceptor = Interceptor { chain ->
            with(chain) {
                val builder: Request.Builder = chain.request().newBuilder()
                val jwtToken: String? = sharedPreferences.getString(X_ACCESS_TOKEN, null)
                if (jwtToken != null) {
                    builder.addHeader(X_ACCESS_TOKEN, jwtToken)
                }

                proceed(builder.build())
            }
        }

        val client = OkHttpClient.Builder().readTimeout(5000, TimeUnit.MILLISECONDS)
            .connectTimeout(5000, TimeUnit.MILLISECONDS).addInterceptor(logger)
            .addNetworkInterceptor(requestInterceptor).build()

        val retrofit = Retrofit.Builder().baseUrl(BASE_URL).client(client)
            .addConverterFactory(GsonConverterFactory.create()).build()

        val database = AppDatabase.getDatabase(this)
        repository = UserInfoRepository(database.userDao(), retrofit)

        KakaoSdk.init(this, BuildConfig.APP_KEY)
    }
}