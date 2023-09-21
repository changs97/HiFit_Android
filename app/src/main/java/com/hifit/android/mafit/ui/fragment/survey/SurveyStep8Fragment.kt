package com.hifit.android.mafit.ui.fragment.survey

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.hifit.android.mafit.R
import com.hifit.android.mafit.base.BaseFragment
import com.hifit.android.mafit.databinding.FragmentSurveyStep8Binding


class SurveyStep8Fragment : BaseFragment<FragmentSurveyStep8Binding>(R.layout.fragment_survey_step8) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.surveyStep8BtnContinue.setOnClickListener {
            findNavController().navigate(R.id.action_surveyStep8Fragment_to_surveyStep9Fragment)
        }
    }
}