package com.hifit.android.mafit.ui.fragment.survey

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.hifit.android.mafit.R
import com.hifit.android.mafit.base.BaseFragment
import com.hifit.android.mafit.databinding.FragmentSurveyStep8Binding
import com.hifit.android.mafit.viewmodel.MainViewModel


class SurveyStep8Fragment :
    BaseFragment<FragmentSurveyStep8Binding>(R.layout.fragment_survey_step8) {
    private val viewModel: MainViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var isCheckedRadio = false

        binding.surveyStep8ImgBack.setOnClickListener {
            findNavController().popBackStack()
        }

        binding.surveyStep8RadioGroup.setOnCheckedChangeListener { _, checkedId ->
            isCheckedRadio = true
            when (checkedId) {
                R.id.survey_step8_radio_btn1 -> {
                    viewModel.surveyInfo.liftPear = 0
                    binding.surveyStep8RadioBtn1.setCompoundDrawablesWithIntrinsicBounds(
                        R.drawable.btn_radio_on, 0, 0, 0
                    )
                    binding.surveyStep8RadioBtn2.setCompoundDrawablesWithIntrinsicBounds(
                        R.drawable.btn_radio_off, 0, 0, 0
                    )
                    binding.surveyStep8RadioBtn3.setCompoundDrawablesWithIntrinsicBounds(
                        R.drawable.btn_radio_off, 0, 0, 0
                    )
                }

                R.id.survey_step8_radio_btn2 -> {
                    viewModel.surveyInfo.liftPear = 1
                    binding.surveyStep8RadioBtn1.setCompoundDrawablesWithIntrinsicBounds(
                        R.drawable.btn_radio_off, 0, 0, 0
                    )
                    binding.surveyStep8RadioBtn2.setCompoundDrawablesWithIntrinsicBounds(
                        R.drawable.btn_radio_on, 0, 0, 0
                    )
                    binding.surveyStep8RadioBtn3.setCompoundDrawablesWithIntrinsicBounds(
                        R.drawable.btn_radio_off, 0, 0, 0
                    )
                }


                else -> {
                    viewModel.surveyInfo.liftPear = 2
                    binding.surveyStep8RadioBtn1.setCompoundDrawablesWithIntrinsicBounds(
                        R.drawable.btn_radio_off, 0, 0, 0
                    )
                    binding.surveyStep8RadioBtn2.setCompoundDrawablesWithIntrinsicBounds(
                        R.drawable.btn_radio_off, 0, 0, 0
                    )
                    binding.surveyStep8RadioBtn3.setCompoundDrawablesWithIntrinsicBounds(
                        R.drawable.btn_radio_on, 0, 0, 0
                    )
                }
            }
        }

        binding.surveyStep8BtnContinue.setOnClickListener {
            if (isCheckedRadio) findNavController().navigate(R.id.action_surveyStep8Fragment_to_surveyStep9Fragment)
        }
    }
}