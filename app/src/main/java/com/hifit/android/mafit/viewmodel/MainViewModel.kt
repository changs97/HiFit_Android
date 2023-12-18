package com.hifit.android.mafit.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.navigation.fragment.findNavController
import com.google.gson.Gson
import com.hifit.android.mafit.HiFitApplication
import com.hifit.android.mafit.HiFitApplication.Companion.repository
import com.hifit.android.mafit.R
import com.hifit.android.mafit.base.BaseResponse
import com.hifit.android.mafit.data.model.home.BodyInfoData
import com.hifit.android.mafit.data.model.home.BodyInfoResponse
import com.hifit.android.mafit.data.model.home.DietData
import com.hifit.android.mafit.data.model.home.DietResponse
import com.hifit.android.mafit.data.model.home.ExerciseData
import com.hifit.android.mafit.data.model.home.ExerciseResponse
import com.hifit.android.mafit.data.model.home.WorkoutInfoData
import com.hifit.android.mafit.data.model.home.WorkoutInfoResponse
import com.hifit.android.mafit.data.model.login.LoginRequestBody
import com.hifit.android.mafit.data.model.survey.HealthInfoRequestBody
import com.hifit.android.mafit.data.repo.UserInfoRepository
import com.hifit.android.mafit.data.source.local.UserInfo
import com.hifit.android.mafit.data.source.network.LoginRetrofitInterface
import com.hifit.android.mafit.util.Constant
import com.hifit.android.mafit.util.Event
import kotlinx.coroutines.launch
import timber.log.Timber

class MainViewModel(private val repository: UserInfoRepository) : ViewModel() {
    val reps: MutableLiveData<Int> = MutableLiveData(0)

    val userInfo: LiveData<UserInfo> = repository.userInfo.asLiveData()
    var userAge = 0
    var userGender = "MALE"
    var userSarcopenia = "HIGH"

    val surveyInfo = HealthInfoRequestBody()

    val bodyInfo: LiveData<BodyInfoData> get() = _bodyInfo
    private val _bodyInfo: MutableLiveData<BodyInfoData> = MutableLiveData()

    val workoutInfo: LiveData<WorkoutInfoData> get() = _workoutInfo
    private val _workoutInfo: MutableLiveData<WorkoutInfoData> = MutableLiveData()

    val exercises: LiveData<List<ExerciseData>> get() = _exercises
    private val _exercises: MutableLiveData<List<ExerciseData>> = MutableLiveData()

    val diet: LiveData<DietData> get() = _diet
    private val _diet: MutableLiveData<DietData> = MutableLiveData()

    val isProgressVisible: LiveData<Boolean> get() = _isProgressVisible
    private val _isProgressVisible: MutableLiveData<Boolean> = MutableLiveData(false)

    private val _navigateNext = MutableLiveData<Event<Boolean>>()
    val navigateNext: LiveData<Event<Boolean>>
        get() = _navigateNext

    private val _showToast = MutableLiveData<Event<String>>()
    val showToast: LiveData<Event<String>>
        get() = _showToast

    private val _errorEvent = MutableLiveData<Event<Int>>()
    val errorEvent: LiveData<Event<Int>>
        get() = _errorEvent


    // TODO: API ERROR 분기 처리를 위한 API 요청 반환 값 Response 래퍼 씌우기,
    //  토큰 만료 시 deleteToken 요청 후 로그인 화면으로 이동하도록 처리

    suspend fun deleteUserInfo() {
        _isProgressVisible.value = true
        viewModelScope.launch {
            try {
                repository.deleteUserInfo()
                repository.deleteToken()
            } catch (e: Exception) {
                Timber.e("network error", e)
                _showToast.value = Event("네트워크 에러가 발생했습니다.")
                _isProgressVisible.value = false
            }
        }
    }

    private fun insertUserInfo(userInfo: UserInfo) {
        _isProgressVisible.value = true
        viewModelScope.launch {
            try {
                repository.insert(userInfo)
            } catch (e: Exception) {
                Timber.e("local error", e)
            } finally {
                _isProgressVisible.value = false
            }
        }
    }

    fun tryPatchUserInfo() {
        viewModelScope.launch {
            _isProgressVisible.value = true

            try {
                val response = repository.patchUserInfo(surveyInfo)
                if (response.isSuccessful) {
                    _navigateNext.value = Event(true)
                } else {
                    val errorBody = response.errorBody()?.string().run {
                        Gson().fromJson(this, BaseResponse::class.java)
                    }

                    val message = errorBody.message
                    val code = errorBody.code

                    if (code == 40103) {
                        deleteUserInfo()
                        _errorEvent.value = Event(40103)
                    }

                    Timber.e("network error: $message")
                    _showToast.value = Event(message)
                }
            } catch (e: Exception) {
                Timber.e("network error", e)
                _showToast.value = Event("네트워크 에러가 발생했습니다.")
            } finally {
                _isProgressVisible.value = false
            }
        }
    }

    fun tryPatchStamps() {
        viewModelScope.launch {
            _isProgressVisible.value = true
            try {
                val response = repository.patchStamps()
                if (response.isSuccessful) {
                    _navigateNext.value = Event(true)
                } else {
                    val errorBody = response.errorBody()?.string().run {
                        Gson().fromJson(this, BaseResponse::class.java)
                    }

                    val message = errorBody.message
                    val code = errorBody.code

                    if (code == 40103) {
                        deleteUserInfo()
                        _errorEvent.value = Event(40103)
                    } else {
                        _navigateNext.value = Event(true)
                    }
                }
            } catch (e: Exception) {
                Timber.e("network error", e)
                _showToast.value = Event("네트워크 에러가 발생했습니다.")
            } finally {
                _isProgressVisible.value = false
            }
        }
    }

    fun tryGetUserInfo() {
        _isProgressVisible.value = true
        viewModelScope.launch {
            try {
                val response = repository.getUserInfo()
                if (response.isSuccessful && response.body() != null) {
                    val userInfo = with(response.body()!!.data) {
                        UserInfo(
                            0,
                            age ?: userAge,
                            diastolicBloodPressure,
                            gender ?: userGender,
                            heartRate,
                            height,
                            name,
                            sarcopenia ?: userSarcopenia,
                            systolicBloodPressure,
                            waistSize,
                            weight
                        )
                    }
                    insertUserInfo(userInfo)
                } else {
                    val errorBody = response.errorBody()?.string().run {
                        Gson().fromJson(this, BaseResponse::class.java)
                    }

                    val message = errorBody.message
                    val code = errorBody.code

                    if (code == 40103) {
                        deleteUserInfo()
                        _errorEvent.value = Event(40103)
                    }

                    Timber.e("network error: $message")
                    _showToast.value = Event(message)
                }
            } catch (e: Exception) {
                Timber.e("network error: $e")
                _showToast.value = Event("네트워크 에러가 발생했습니다.")
            } finally {
                _isProgressVisible.value = false
            }
        }
    }

    fun tryPostLogin(token: String) {
        _isProgressVisible.value = true
        viewModelScope.launch {
            try {
                val response = repository.postLogin(LoginRequestBody(token))
                if (response.isSuccessful && response.body() != null) {
                    storeToken(response.body()!!.data.code)
                    _navigateNext.value = Event(true)
                } else {
                    val errorBody = response.errorBody()?.string().run {
                        Gson().fromJson(this, BaseResponse::class.java)
                    }

                    val message = errorBody.message
                    val code = errorBody.code

                    if (code == 40103) {
                        deleteUserInfo()
                        _errorEvent.value = Event(40103)
                    }

                    Timber.e("network error: $message")
                    _showToast.value = Event(message)
                }

            } catch (e: java.lang.Exception) {
                Timber.e("network error: $e")
                _showToast.value = Event("네트워크 에러가 발생했습니다.")
            } finally {
                _isProgressVisible.value = false
            }
        }
    }

    fun tryGetBodyInfo() {
        viewModelScope.launch {
            _isProgressVisible.value = true
            try {
                val response = repository.getBodyInfo()
                if (response.isSuccessful && response.body() != null) {
                    _bodyInfo.value = response.body()!!.data
                } else {
                    val errorBody = response.errorBody()?.string().run {
                        Gson().fromJson(this, BaseResponse::class.java)
                    }

                    val message = errorBody.message
                    val code = errorBody.code

                    if (code == 40103) {
                        deleteUserInfo()
                        _errorEvent.value = Event(40103)
                    }

                    Timber.e("network error: $message")
                    _showToast.value = Event(message)
                }
            } catch (e: Exception) {
                Timber.e("network error: $e")
                _showToast.value = Event("네트워크 에러가 발생했습니다.")
            } finally {
                _isProgressVisible.value = false
            }
        }
    }

    fun tryGetExercises() {
        viewModelScope.launch {
            _isProgressVisible.value = true
            try {
                val response = repository.getExercises()
                if (response.isSuccessful && response.body() != null) {
                    _exercises.value = response.body()!!.data
                } else {
                    val errorBody = response.errorBody()?.string().run {
                        Gson().fromJson(this, BaseResponse::class.java)
                    }

                    val message = errorBody.message
                    val code = errorBody.code

                    if (code == 40103) {
                        deleteUserInfo()
                        _errorEvent.value = Event(40103)
                    }

                    Timber.e("network error: $message")
                    _showToast.value = Event(message)
                }
            } catch (e: Exception) {
                Timber.e("network error: $e")
                _showToast.value = Event("네트워크 에러가 발생했습니다.")
            } finally {
                _isProgressVisible.value = false
            }
        }
    }

    fun tryGetDiet() {
        viewModelScope.launch {
            _isProgressVisible.value = true
            try {
                val response = repository.getDiet()
                if (response.isSuccessful && response.body() != null) {
                    _diet.value = response.body()!!.data
                } else {
                    val errorBody = response.errorBody()?.string().run {
                        Gson().fromJson(this, BaseResponse::class.java)
                    }

                    val message = errorBody.message
                    val code = errorBody.code

                    if (code == 40103) {
                        deleteUserInfo()
                        _errorEvent.value = Event(40103)
                    }

                    Timber.e("network error: $message")
                    _showToast.value = Event(message)
                }
            } catch (e: Exception) {
                Timber.e("network error: $e")
                _showToast.value = Event("네트워크 에러가 발생했습니다.")
            } finally {
                _isProgressVisible.value = false
            }
        }
    }

    fun tryGetWorkoutInfo() {
        viewModelScope.launch {
            _isProgressVisible.value = true
            try {
                val response = repository.getWorkoutInfo()
                if (response.isSuccessful && response.body() != null) {
                    _workoutInfo.value = response.body()!!.data
                } else {
                    val errorBody = response.errorBody()?.string().run {
                        Gson().fromJson(this, BaseResponse::class.java)
                    }

                    val message = errorBody.message
                    val code = errorBody.code

                    if (code == 40103) {
                        deleteUserInfo()
                        _errorEvent.value = Event(40103)
                    }

                    Timber.e("network error: $message")
                    _showToast.value = Event(message)
                }
            } catch (e: Exception) {
                Timber.e("network error: $e")
                _showToast.value = Event("네트워크 에러가 발생했습니다.")
            } finally {
                _isProgressVisible.value = false
            }
        }
    }

    private fun storeToken(token: String) {
        try {
            repository.storeToken(token)
        } catch (e: Exception) {
            Timber.e("local error: $e")
        }
    }

    private fun deleteToken() {
        try {
            repository.deleteToken()
        } catch (e: Exception) {
            Timber.e("local error: $e")
        }
    }

    fun getToken() = repository.getToken()

    companion object {
        val Factory: ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(
                modelClass: Class<T>, extras: CreationExtras
            ): T {
                return MainViewModel(
                    repository
                ) as T
            }
        }
    }
}