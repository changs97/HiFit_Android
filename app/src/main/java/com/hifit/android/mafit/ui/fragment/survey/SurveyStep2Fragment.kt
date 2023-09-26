package com.hifit.android.mafit.ui.fragment.survey

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.hifit.android.mafit.R
import com.hifit.android.mafit.base.BaseFragment
import com.hifit.android.mafit.databinding.FragmentSurveyStep2Binding
import com.hifit.android.mafit.viewmodel.MainViewModel


class SurveyStep2Fragment : BaseFragment<FragmentSurveyStep2Binding>(R.layout.fragment_survey_step2) {
    private val viewModel: MainViewModel by activityViewModels()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.surveyStep2BtnContinue.setOnClickListener {
            if (binding.surveyStep2Edit.text.isNullOrEmpty()) {
                showCustomToast("나이를 입력해주세요")
            } else {
                viewModel.userAge = binding.surveyStep2Edit.text.toString().toInt()
                findNavController().navigate(R.id.action_surveyStep2Fragment_to_surveyStep3Fragment)
            }
        }

    }
}