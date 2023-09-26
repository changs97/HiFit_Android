package com.hifit.android.mafit.data.model.home

import com.google.gson.annotations.SerializedName

data class ExerciseData(
    @SerializedName("id")
    val id: Int,
    @SerializedName("workoutDate")
    val workoutDate: String
)