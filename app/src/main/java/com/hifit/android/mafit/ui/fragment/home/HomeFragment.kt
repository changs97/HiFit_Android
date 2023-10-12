package com.hifit.android.mafit.ui.fragment.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.hifit.android.mafit.R
import com.hifit.android.mafit.base.BaseFragment
import com.hifit.android.mafit.databinding.FragmentHomeBinding
import com.hifit.android.mafit.databinding.FragmentSurveyCompleteBinding
import com.hifit.android.mafit.ui.MainActivity
import com.hifit.android.mafit.viewmodel.MainViewModel
import timber.log.Timber
import java.text.DecimalFormat

class HomeFragment : BaseFragment<FragmentHomeBinding>(R.layout.fragment_home) {
    private val viewModel: MainViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.vm = viewModel

        with(viewModel) {
            tryGetUserInfo()
            tryGetBodyInfo()
            tryGetDiet()
            tryGetWorkoutInfo()
        }

        viewModel.showToast.observe(viewLifecycleOwner) { event ->
            event.getContentIfNotHandled()?.let {
                if (it.isNotEmpty()) showCustomToast(it)
            }
        }

        viewModel.errorEvent.observe(viewLifecycleOwner) { event ->
            event.getContentIfNotHandled()?.let {
                if (it == 40103) activity?.finish()
            }
        }

        viewModel.bodyInfo.observe(viewLifecycleOwner) { bodyInfo ->
            Timber.tag("테스트").d("${bodyInfo?.currentBmi}")
            bodyInfo?.currentBmi?.let {
                val bmi = String.format("%.1f", it)

                val bmiName = if (it <= 18.5) {
                    "저체중"
                } else if (it > 18.5 && it <= 22.9) {
                    "정상체중"
                } else if (it in 23.0..24.9) {
                    "과체중"
                } else if (it in 25.0..29.9) {
                    "비만"
                } else "고도비만"

                binding.homeTxtBmi.text = "$bmi $bmiName"
            }
        }

        viewModel.userInfo.observe(viewLifecycleOwner) { userInfo ->
            Timber.tag("테스트").d("$userInfo")
            userInfo?.let {
                var sarcopeniaName = ""
                when (userInfo.sarcopenia) {
                    "LOW" -> {
                        sarcopeniaName = "저위험군"
                    }

                    "MEDIUM" -> {
                        sarcopeniaName = "중위험군"
                    }

                    "HIGH" -> {
                        sarcopeniaName = "고위험군"
                    }

                    else -> {
                        // TODO: 빈 화면 또는 에러 화면 표시
                    }
                }
                binding.homeTxtResultType.text = sarcopeniaName
            }
        }

        binding.homeLlCoinContainer.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_productFragment)
        }

        binding.homeBtnStamp.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_exerciseFragment)
        }

        binding.homeBtnResult.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_surveyCompleteFragment)
        }
    }
}