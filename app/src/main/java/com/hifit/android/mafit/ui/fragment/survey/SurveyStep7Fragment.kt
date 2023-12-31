package com.hifit.android.mafit.ui.fragment.survey

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.hifit.android.mafit.R
import com.hifit.android.mafit.base.BaseFragment
import com.hifit.android.mafit.databinding.FragmentSurveyStep7Binding


class SurveyStep7Fragment : BaseFragment<FragmentSurveyStep7Binding>(R.layout.fragment_survey_step7) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.surveyStep7ImgBack.setOnClickListener {
            findNavController().popBackStack()
        }

        binding.surveyStep7BtnContinue.setOnClickListener {
            findNavController().navigate(R.id.action_surveyStep7Fragment_to_surveyStep8Fragment)
        }
    }
}