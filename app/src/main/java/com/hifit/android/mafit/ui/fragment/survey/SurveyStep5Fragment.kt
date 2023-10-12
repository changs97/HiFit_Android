package com.hifit.android.mafit.ui.fragment.survey

import android.os.Bundle
import android.view.View
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.hifit.android.mafit.R
import com.hifit.android.mafit.base.BaseFragment
import com.hifit.android.mafit.databinding.FragmentSurveyStep5Binding
import com.hifit.android.mafit.viewmodel.MainViewModel


class SurveyStep5Fragment : BaseFragment<FragmentSurveyStep5Binding>(R.layout.fragment_survey_step5) {
    private val viewModel: MainViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.surveyStep5ImgBack.setOnClickListener {
            findNavController().popBackStack()
        }

        binding.surveyStep5Edit.addTextChangedListener {
            if (binding.surveyStep5Edit.isFocused) {
                binding.surveyStep5Edit.setBackgroundResource(R.drawable.background_stroke_solid_violet5)
            } else {
                if (binding.surveyStep5Edit.text.isNullOrEmpty()) {
                    binding.surveyStep5Edit.setBackgroundResource(R.drawable.background_stroke_gray7)
                } else {
                    binding.surveyStep5Edit.setBackgroundResource(R.drawable.background_radius_10dp_stroke_violet)
                }
            }
        }

        binding.surveyStep5BtnContinue.setOnClickListener {
            if (binding.surveyStep5Edit.text.isNullOrEmpty()) {
                showCustomToast("허리 둘레를 입력해주세요")
            } else {
                viewModel.surveyInfo.waistSize = binding.surveyStep5Edit.text.toString().toInt()
                findNavController().navigate(R.id.action_surveyStep5Fragment_to_surveyStep6Fragment)
            }
        }
    }
}