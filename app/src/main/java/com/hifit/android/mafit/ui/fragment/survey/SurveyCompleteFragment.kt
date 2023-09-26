package com.hifit.android.mafit.ui.fragment.survey

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.hifit.android.mafit.R
import com.hifit.android.mafit.base.BaseFragment
import com.hifit.android.mafit.databinding.FragmentSurveyCompleteBinding
import com.hifit.android.mafit.viewmodel.MainViewModel
import timber.log.Timber

class SurveyCompleteFragment :
    BaseFragment<FragmentSurveyCompleteBinding>(R.layout.fragment_survey_complete) {
    private val viewModel: MainViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.vm = viewModel

        viewModel.tryGetUserInfo()

        viewModel.showToast.observe(viewLifecycleOwner) { event ->
            event.getContentIfNotHandled()?.let {
                if (it) showCustomToast(getString(R.string.network_error_message))
            }
        }

        viewModel.userInfo.observe(viewLifecycleOwner) { userInfo ->
            Timber.tag("테스트").d("$userInfo")
            userInfo?.let {
                var imgResourceId: Int? = null
                var sarcopeniaName = ""
                var contents = ""
                var textColor = resources.getColor(R.color.yellow, null)
                when (userInfo.sarcopenia) {
                    "LOW" -> {
                        imgResourceId = R.drawable.ic_survey_result1
                        sarcopeniaName = "저위험군"
                        textColor = resources.getColor(R.color.green, null)
                        contents = getString(R.string.survey_complete_contents1)
                    }

                    "MEDIUM" -> {
                        imgResourceId = R.drawable.ic_survey_result2
                        sarcopeniaName = "중위험군"
                        textColor = resources.getColor(R.color.yellow, null)
                        contents = getString(R.string.survey_complete_contents2)
                    }

                    "HIGH" -> {
                        imgResourceId = R.drawable.ic_survey_result3
                        sarcopeniaName = "고위험군"
                        textColor = resources.getColor(R.color.red, null)
                        contents = getString(R.string.survey_complete_contents3)
                    }

                    else -> {
                        // TODO: 빈 화면 또는 에러 화면 표시
                    }
                }

                Glide.with(this).load(imgResourceId).centerCrop()
                    .into(binding.surveyCompleteImgResult)

                binding.surveyCompleteTxtResultType.text = sarcopeniaName
                binding.surveyCompleteTxtResultType.setTextColor(textColor)
                binding.surveyCompleteTxtContents.text = contents

                binding.surveyCompleteTxtName.text = it.name
                binding.surveyCompleteTxtAge.text = it.age.toString()
                binding.surveyCompleteTxtSex.text = when (it.gender) {
                    "MALE" -> "남성"
                    "FEMALE" -> "여성"
                    else -> ""
                }
            }
        }

        binding.surveyCompleteBtnRecommend.setOnClickListener {
            findNavController().navigate(R.id.action_surveyCompleteFragment_to_homeFragment)
        }
    }
}