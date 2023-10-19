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

        binding.surveyStep10ImgBack.setOnClickListener {
            findNavController().popBackStack()
        }

        var isCheckedRadio = false

        binding.surveyStep10RadioGroup.setOnCheckedChangeListener { _, checkedId ->
            isCheckedRadio = true
            when (checkedId) {
                R.id.survey_step10_radio_btn1 -> {
                    binding.surveyStep10RadioBtn1.setCompoundDrawablesWithIntrinsicBounds(
                        R.drawable.btn_radio_on, 0, 0, 0
                    )
                    binding.surveyStep10RadioBtn2.setCompoundDrawablesWithIntrinsicBounds(
                        R.drawable.btn_radio_off, 0, 0, 0
                    )
                    binding.surveyStep10RadioBtn3.setCompoundDrawablesWithIntrinsicBounds(
                        R.drawable.btn_radio_off, 0, 0, 0
                    )
                }

                R.id.survey_step10_radio_btn2 -> {
                    binding.surveyStep10RadioBtn1.setCompoundDrawablesWithIntrinsicBounds(
                        R.drawable.btn_radio_off, 0, 0, 0
                    )
                    binding.surveyStep10RadioBtn2.setCompoundDrawablesWithIntrinsicBounds(
                        R.drawable.btn_radio_on, 0, 0, 0
                    )
                    binding.surveyStep10RadioBtn3.setCompoundDrawablesWithIntrinsicBounds(
                        R.drawable.btn_radio_off, 0, 0, 0
                    )
                }


                else -> {
                    binding.surveyStep10RadioBtn1.setCompoundDrawablesWithIntrinsicBounds(
                        R.drawable.btn_radio_off, 0, 0, 0
                    )
                    binding.surveyStep10RadioBtn2.setCompoundDrawablesWithIntrinsicBounds(
                        R.drawable.btn_radio_off, 0, 0, 0
                    )
                    binding.surveyStep10RadioBtn3.setCompoundDrawablesWithIntrinsicBounds(
                        R.drawable.btn_radio_on, 0, 0, 0
                    )
                }
            }
        }

        binding.surveyStep10BtnContinue.setOnClickListener {
            if (isCheckedRadio) findNavController().navigate(R.id.action_surveyStep10Fragment_to_surveyStep11Fragment)
        }
    }
}