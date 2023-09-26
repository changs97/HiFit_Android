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
import com.hifit.android.mafit.HiFitApplication
import com.hifit.android.mafit.HiFitApplication.Companion.repository
import com.hifit.android.mafit.R
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

    private val _showToast = MutableLiveData<Event<Boolean>>()
    val showToast: LiveData<Event<Boolean>>
        get() = _showToast

    fun deleteUserInfo() {
        _isProgressVisible.value = true
        viewModelScope.launch {
            try {
                repository.deleteUserInfo()
            } catch (e: Exception) {
                Timber.e("network error", e)
                _showToast.value = Event(true)
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
                _showToast.value = Event(true)
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
                if (response.code == 200) {
                    _navigateNext.value = Event(true)
                } else {
                    Timber.e("network error: ${response.message}")
                    _showToast.value = Event(true)
                }
            } catch (e: Exception) {
                Timber.e("network error", e)
                _showToast.value = Event(true)
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
                if (response.code == 200) {
                    _navigateNext.value = Event(true)
                } else {
                    Timber.e("network error: ${response.message}")
                    _showToast.value = Event(true)
                }
            } catch (e: Exception) {
                Timber.e("network error", e)
                _showToast.value = Event(true)
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
                if (response.code == 200) {
                    val userInfo = with(response.data) {
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
                    Timber.e("network error: ${response.message}")
                    _showToast.value = Event(true)
                }
            } catch (e: Exception) {
                Timber.e("network error: $e")
                _showToast.value = Event(true)
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
                if (response.code == 200) {
                    storeToken(response.data.code)
                    _navigateNext.value = Event(true)
                } else {
                    Timber.e("network error: ${response.message}")
                    _showToast.value = Event(true)
                }

            } catch (e: java.lang.Exception) {
                Timber.e("network error: $e")
                _showToast.value = Event(true)
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
                if (response.code == 200) {
                    _bodyInfo.value = response.data
                } else {
                    Timber.e("network error: ${response.message}")
                    _showToast.value = Event(true)
                }
            } catch (e: Exception) {
                Timber.e("network error: $e")
                _showToast.value = Event(true)
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
                if (response.code == 200) {
                    _exercises.value = response.data
                } else {
                    Timber.e("network error: ${response.message}")
                    _showToast.value = Event(true)
                }
            } catch (e: Exception) {
                Timber.e("network error: $e")
                _showToast.value = Event(true)
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
                if (response.code == 200) {
                    _diet.value = response.data
                } else {
                    Timber.e("network error: ${response.message}")
                    _showToast.value = Event(true)
                }
            } catch (e: Exception) {
                Timber.e("network error: $e")
                _showToast.value = Event(true)
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
                if (response.code == 200) {
                    _workoutInfo.value = response.data
                } else {
                    Timber.e("network error: ${response.message}")
                    _showToast.value = Event(true)
                }
            } catch (e: Exception) {
                Timber.e("network error: $e")
                _showToast.value = Event(true)
            } finally {
                _isProgressVisible.value = false
            }
        }
    }

    private fun storeToken(token: String) {
        try {
            repository.storeToken(token)
        } catch (e: java.lang.Exception) {
            Timber.e("local error: $e")
            _showToast.value = Event(true)
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