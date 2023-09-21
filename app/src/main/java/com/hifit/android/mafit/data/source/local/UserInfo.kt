package com.hifit.android.mafit.data.source.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_info")
data class UserInfo(
    @PrimaryKey var id: Int,
    var age: Int?,
    var diastolicBloodPressure: Int? ,
    var gender: String?,
    var heartRate: Int?,
    var height: Int?,
    var name: String?,
    var sarcopenia: String?,
    var systolicBloodPressure: Int?,
    var waistSize: Int?,
    var weight: Int?
)