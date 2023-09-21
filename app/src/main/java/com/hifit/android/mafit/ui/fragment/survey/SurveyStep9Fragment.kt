package com.hifit.android.mafit.ui.fragment.survey

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.hifit.android.mafit.R
import com.hifit.android.mafit.base.BaseFragment
import com.hifit.android.mafit.databinding.FragmentSurveyStep9Binding


class SurveyStep9Fragment : BaseFragment<FragmentSurveyStep9Binding>(R.layout.fragment_survey_step9) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.surveyStep9BtnContinue.setOnClickListener {
            findNavController().navigate(R.id.action_surveyStep9Fragment_to_surveyStep10Fragment)
        }
    }
}