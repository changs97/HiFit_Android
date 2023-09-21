package com.hifit.android.mafit.ui.fragment.survey

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.hifit.android.mafit.R
import com.hifit.android.mafit.base.BaseFragment
import com.hifit.android.mafit.databinding.FragmentSurveyStep6Binding
import com.hifit.android.mafit.viewmodel.MainViewModel


class SurveyStep6Fragment : BaseFragment<FragmentSurveyStep6Binding>(R.layout.fragment_survey_step6) {
    private val viewModel: MainViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.surveyStep6BtnContinue.setOnClickListener {
            if (binding.surveyStep6Edit.text.isNullOrEmpty()) {
                showCustomToast("허리 둘레를 입력해주세요")
            } else {
                viewModel.surveyInfo.waistSize = binding.surveyStep6Edit.text.toString().toInt()
                findNavController().navigate(R.id.action_surveyStep6Fragment_to_surveyStep7Fragment)
            }
        }
    }
}