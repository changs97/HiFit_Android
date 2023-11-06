package com.hifit.android.mafit.ui.fragment.my

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.hifit.android.mafit.HiFitApplication
import com.hifit.android.mafit.R
import com.hifit.android.mafit.base.BaseFragment
import com.hifit.android.mafit.databinding.FragmentMyPageBinding
import com.hifit.android.mafit.viewmodel.MainViewModel
import com.kakao.sdk.user.UserApiClient
import timber.log.Timber


class MyPageFragment : BaseFragment<FragmentMyPageBinding>(R.layout.fragment_my_page) {
    private val viewModel: MainViewModel by activityViewModels()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.vm = viewModel

        binding.myLlPointStampContainer.setOnClickListener {
            findNavController().navigate(R.id.action_myPageFragment_to_calenderFragment)
        }

        binding.myTxtSurvey.setOnClickListener {
            findNavController().navigate(R.id.action_myPageFragment_to_survey_graph)
        }

        binding.myTxtLogout.setOnClickListener {
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