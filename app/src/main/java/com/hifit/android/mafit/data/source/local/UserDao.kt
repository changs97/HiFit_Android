package com.hifit.android.mafit.data.source.local


import androidx.room.*
import kotlinx.coroutines.flow.Flow
import kotlin.coroutines.RestrictsSuspension

@Dao
interface UserInfoDao {
    @Query("SELECT * FROM user_info WHERE id = :id")
    fun loadUserById(id: Int = 0): Flow<UserInfo>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUserInfo(userInfo: UserInfo)

    @Query("DELETE FROM user_info")
    suspend fun deleteAllUsers()
}