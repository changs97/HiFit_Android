package com.hifit.android.mafit.ui.fragment.my

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.hifit.android.mafit.HiFitApplication
import com.hifit.android.mafit.R
import com.hifit.android.mafit.base.BaseFragment
import com.hifit.android.mafit.databinding.FragmentMyPageBinding
import com.hifit.android.mafit.ui.MainActivity
import com.hifit.android.mafit.util.LogoutDialog
import com.hifit.android.mafit.viewmodel.MainViewModel
import com.kakao.sdk.user.UserApiClient
import kotlinx.coroutines.launch
import timber.log.Timber


class MyPageFragment : BaseFragment<FragmentMyPageBinding>(R.layout.fragment_my_page) {
    private val viewModel: MainViewModel by activityViewModels()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.vm = viewModel

        viewModel.tryGetWorkoutInfo()

        binding.myLlPointStampContainer.setOnClickListener {
            findNavController().navigate(R.id.action_myPageFragment_to_calenderFragment)
        }

        binding.myTxtSurvey.setOnClickListener {
            findNavController().navigate(R.id.action_myPageFragment_to_survey_graph)
        }

        binding.myTxtSurvey.setOnClickListener {
            showCustomToast("아직 구현되지 않은 기능입니다.")
        }

        binding.myTxtWithdrawal.setOnClickListener {
            showCustomToast("아직 구현되지 않은 기능입니다.")
        }

        binding.myTxtLogout.setOnClickListener {
            val dialog = LogoutDialog {
                HiFitApplication.sharedPreferences.edit().clear().apply()
                kakaoLogout()
            }

            dialog.show(parentFragmentManager, null)

        }

        viewModel.showToast.observe(viewLifecycleOwner) { event ->
            event.getContentIfNotHandled()?.let {
                if (it.isNotEmpty()) showCustomToast(it)
            }
        }

        viewModel.errorEvent.observe(viewLifecycleOwner) { event ->
            event.getContentIfNotHandled()?.let {
                if (it == 40103) {
                    val intent = Intent(requireContext(), MainActivity::class.java)
                    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    startActivity(intent)
                }
            }
        }
    }

    private fun kakaoLogout() {
        UserApiClient.instance.unlink { error ->
            if (error != null) {
                Timber.e("연결 끊기 실패", error)
            } else {
                Timber.i("연결 끊기 성공")
                viewLifecycleOwner.lifecycleScope.launch {
                    viewModel.deleteUserInfo()
                    val intent = Intent(requireContext(), MainActivity::class.java)
                    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    startActivity(intent)
                }
            }
        }
    }
}