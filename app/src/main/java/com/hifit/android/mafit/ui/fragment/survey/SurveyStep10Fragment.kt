package com.hifit.android.mafit.ui.fragment.survey

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.hifit.android.mafit.R
import com.hifit.android.mafit.base.BaseFragment
import com.hifit.android.mafit.databinding.FragmentSurveyStep10Binding


class SurveyStep10Fragment : BaseFragment<FragmentSurveyStep10Binding>(R.layout.fragment_survey_step10) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.surveyStep10BtnContinue.setOnClickListener {
            findNavController().navigate(R.id.action_surveyStep10Fragment_to_surveyStep11Fragment)
        }
    }
}