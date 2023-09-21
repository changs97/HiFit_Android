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
    val surveyInfo = HealthInfoRequestBody()
    val isProgressVisible: LiveData<Boolean> get() = _isProgressVisible
    private val _isProgressVisible: MutableLiveData<Boolean> = MutableLiveData(false)

    private val _navigateNext = MutableLiveData<Event<Boolean>>()
    val navigateNext: LiveData<Event<Boolean>>
        get() = _navigateNext

    fun deleteUserInfo() {
        _isProgressVisible.value = true
        viewModelScope.launch {
            try {
                repository.deleteUserInfo()
            } catch (e: Exception) {
                Timber.e("network error", e)
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

    fun patchUserInfo() {
        viewModelScope.launch {
            _isProgressVisible.value = true

            try {
                val response = repository.patchUserInfo(surveyInfo)
                if (response.code == 200) {
                    _navigateNext.value = Event(true)
                } else {
                    Timber.e("network error: ${response.message}")
                }
            } catch (e: Exception) {
                Timber.e("network error", e)
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
                            age,
                            diastolicBloodPressure,
                            gender,
                            heartRate,
                            height,
                            name,
                            sarcopenia,
                            systolicBloodPressure,
                            waistSize,
                            weight
                        )
                    }
                    insertUserInfo(userInfo)
                } else {
                    Timber.e("network error: ${response.message}")
                }
            } catch (e: Exception) {
                Timber.e("network error: $e")
            } finally {
                _isProgressVisible.value = false
            }
        }
    }

    fun postLogin(token: String) {
        _isProgressVisible.value = true
        viewModelScope.launch {
            try {
                val response = repository.postLogin(LoginRequestBody(token))
                if (response.code == 200) {
                    storeToken(response.data.code)
                    _navigateNext.value = Event(true)
                } else {
                    Timber.e("network error: ${response.message}")
                }

            } catch (e: java.lang.Exception) {
                Timber.e("network error: $e")
            } finally {
                _isProgressVisible.value = false
            }
        }
    }

    private fun storeToken(token: String) {
        try {
            repository.storeToken(token)
        } catch (e: java.lang.Exception) {
            Timber.e("network error: $e")
        }
    }

    fun getToken() = repository.getToken()

    companion object {

        val Factory: ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(
                modelClass: Class<T>,
                extras: CreationExtras
            ): T {
                return MainViewModel(
                    repository
                ) as T
            }
        }
    }
}