package com.hifit.android.mafit.ui.fragment.start

import android.content.Intent
import android.content.Intent.FLAG_ACTIVITY_CLEAR_TASK
import android.content.Intent.FLAG_ACTIVITY_NEW_TASK
import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.hifit.android.mafit.HiFitApplication
import com.hifit.android.mafit.R
import com.hifit.android.mafit.base.BaseFragment
import com.hifit.android.mafit.data.source.local.UserInfo
import com.hifit.android.mafit.databinding.FragmentStartBinding
import com.hifit.android.mafit.ui.HomeActivity
import com.hifit.android.mafit.viewmodel.MainViewModel
import com.kakao.sdk.user.UserApiClient
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import timber.log.Timber

class StartFragment : BaseFragment<FragmentStartBinding>(R.layout.fragment_start) {
    private val viewModel: MainViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var userSurveyParticipation: UserInfo? = null
        viewModel.userInfo.observe(viewLifecycleOwner) {
            userSurveyParticipation = it
        }

        viewLifecycleOwner.lifecycleScope.launch {
            delay(1000)
            viewModel.getToken()?.let {
                Timber.tag("토큰").d("$it")
                if (userSurveyParticipation != null) {
                    val intent = Intent(requireContext(), HomeActivity::class.java)
                    intent.flags = FLAG_ACTIVITY_NEW_TASK or FLAG_ACTIVITY_CLEAR_TASK
                    startActivity(intent)
                } else {
                    findNavController().navigate(R.id.action_startFragment_to_surveyStartFragment)
                }
            } ?: run {
                findNavController().navigate(R.id.action_startFragment_to_loginFragment)
            }
        }

        binding.startImg.setOnClickListener {
            HiFitApplication.sharedPreferences.edit().clear().apply()
            kakaoLogout()
        }
    }

    private fun kakaoLogout() {
        UserApiClient.instance.unlink { error ->
            if (error != null) {
                Timber.e("연결 끊기 실패", error)
            } else {
                Timber.i("연결 끊기 성공")
                viewModel.deleteUserInfo()
                activity?.finish()
            }
        }
    }

}