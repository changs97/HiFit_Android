package com.hifit.android.mafit.ui.fragment.survey

import android.os.Bundle
import android.view.View
import android.widget.RadioGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.hifit.android.mafit.R
import com.hifit.android.mafit.base.BaseFragment
import com.hifit.android.mafit.databinding.FragmentSurveyStartBinding
import com.hifit.android.mafit.databinding.FragmentSurveyStep1Binding
import com.hifit.android.mafit.viewmodel.MainViewModel


class SurveyStep1Fragment :
    BaseFragment<FragmentSurveyStep1Binding>(R.layout.fragment_survey_step1) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var gender: String?  = null

        binding.surveyStep1BtnContinue.setOnClickListener {
            if (gender == null) {
                showCustomToast("성별을 선택해주세요")
            } else {
                findNavController().navigate(R.id.action_surveyStep1Fragment_to_surveyStep2Fragment)
            }
        }

        binding.surveyStep1RadioGroup.setOnCheckedChangeListener { group, checkedId ->
            gender = when (checkedId) {
                R.id.survey_step1_radio_btn_male -> "MALE"
                R.id.survey_step1_radio_btn_female -> "FEMALE"
                else -> null
            }

        }
    }
}