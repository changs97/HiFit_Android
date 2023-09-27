package com.hifit.android.mafit.data.source.network

import com.hifit.android.mafit.base.BaseResponse
import com.hifit.android.mafit.data.model.home.BodyInfoResponse
import com.hifit.android.mafit.data.model.home.DietResponse
import com.hifit.android.mafit.data.model.home.ExerciseResponse
import com.hifit.android.mafit.data.model.home.WorkoutInfoResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.PATCH

interface HomeRetrofitInterface {
    @GET("/users/exercises")
    suspend fun getExercises(): ExerciseResponse

    @GET("/users/diet")
    suspend fun getDiet(): DietResponse

    @GET("/users/bodyInfo")
    suspend fun getBodyInfo(): BodyInfoResponse

    @GET("/users/workoutInfo")
    suspend fun getWorkoutInfo(): WorkoutInfoResponse

    @PATCH("/users/stamps")
    suspend fun patchStamps(): Response<BaseResponse>
}