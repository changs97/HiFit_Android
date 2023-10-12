package com.hifit.android.mafit.ui.fragment.survey

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.hifit.android.mafit.R
import com.hifit.android.mafit.base.BaseFragment
import com.hifit.android.mafit.databinding.FragmentSurveyCompleteBinding
import com.hifit.android.mafit.ui.HomeActivity
import com.hifit.android.mafit.viewmodel.MainViewModel
import timber.log.Timber
import java.text.DecimalFormat

class SurveyCompleteFragment :
    BaseFragment<FragmentSurveyCompleteBinding>(R.layout.fragment_survey_complete) {
    private val viewModel: MainViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.vm = viewModel

        viewModel.tryGetUserInfo()
        viewModel.tryGetDiet()
        viewModel.tryGetBodyInfo()

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

                binding.surveyCompleteTxtBmi.text = "$bmi $bmiName"
            }
        }

        viewModel.userInfo.observe(viewLifecycleOwner) { userInfo ->
            Timber.tag("테스트").d("$userInfo")
            userInfo?.let {
                val height = it.height
                val weight = it.weight

                if (height != null && weight != null) {
                    val standardWeight = (height - 100) * 0.9
                    val recommendedCalorie = standardWeight * 30

                    binding.surveyCompleteTxtContent5Kcal.text =
                        DecimalFormat("#,###").format(recommendedCalorie.toInt())

                    val proteinIntake = weight * 1.2

                    binding.surveyCompleteTxtContent5Intake.text =
                        DecimalFormat("#,###").format(proteinIntake.toInt())
                }
                var imgResourceId: Int? = null
                var sarcopeniaName = ""
                var contents = ""
                var textColor = resources.getColor(R.color.yellow, null)
                when (userInfo.sarcopenia) {
                    "LOW" -> {
                        imgResourceId = R.drawable.ic_square_mint
                        sarcopeniaName = "저위험군"
                        textColor = resources.getColor(R.color.green2, null)
                        contents = getString(R.string.survey_complete_contents1)
                    }

                    "MEDIUM" -> {
                        imgResourceId = R.drawable.ic_square_yellow
                        sarcopeniaName = "중위험군"
                        textColor = resources.getColor(R.color.yellow2, null)
                        contents = getString(R.string.survey_complete_contents2)
                    }

                    "HIGH" -> {
                        imgResourceId = R.drawable.ic_square_red
                        sarcopeniaName = "고위험군"
                        textColor = resources.getColor(R.color.red2, null)
                        contents = getString(R.string.survey_complete_contents3)
                    }

                    else -> {
                        // TODO: 빈 화면 또는 에러 화면 표시
                    }
                }

                Glide.with(this).load(imgResourceId).centerCrop()
                    .into(binding.surveyCompleteImgResult)
                binding.surveyCompleteTxtResultType.text = sarcopeniaName
                binding.surveyCompleteTxtResultType2.text = sarcopeniaName
                binding.surveyCompleteTxtResultType2.setTextColor(textColor)
                binding.surveyCompleteTxtContents.text = contents

                binding.surveyCompleteTxtName.text = it.name
                binding.surveyCompleteTxtAge.text = it.age.toString()
                binding.surveyCompleteTxtGender.text = when (it.gender) {
                    "MALE" -> "남성"
                    "FEMALE" -> "여성"
                    else -> ""
                }
            }
        }

        binding.surveyCompleteBtnRecommend.setOnClickListener {
            if (findNavController().graph.id == R.id.nav_graph) {
                val intent = Intent(requireContext(), HomeActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(intent)
            } else {
                findNavController().navigate(R.id.action_surveyCompleteFragment_to_homeFragment)
            }
        }
    }
}