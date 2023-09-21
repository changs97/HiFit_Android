package com.hifit.android.mafit.ui.fragment.survey

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.hifit.android.mafit.R
import com.hifit.android.mafit.base.BaseFragment
import com.hifit.android.mafit.databinding.FragmentSurveyStartBinding
import com.hifit.android.mafit.databinding.FragmentSurveyStep4Binding
import com.hifit.android.mafit.viewmodel.MainViewModel


class SurveyStep4Fragment : BaseFragment<FragmentSurveyStep4Binding>(R.layout.fragment_survey_step4) {
    private val viewModel: MainViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.surveyStep4BtnContinue.setOnClickListener {
            if (binding.surveyStep4Edit.text.isNullOrEmpty()) {
                showCustomToast("몸무게를 입력해주세요")
            } else {
                viewModel.surveyInfo.weight = binding.surveyStep4Edit.text.toString().toInt()
                findNavController().navigate(R.id.action_surveyStep4Fragment_to_surveyStep5Fragment)
            }
        }
    }
}