package com.hifit.android.mafit.ui.fragment.survey

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.hifit.android.mafit.R
import com.hifit.android.mafit.base.BaseFragment
import com.hifit.android.mafit.databinding.FragmentSurveyStep9Binding
import com.hifit.android.mafit.viewmodel.MainViewModel


class SurveyStep9Fragment : BaseFragment<FragmentSurveyStep9Binding>(R.layout.fragment_survey_step9) {
    private val viewModel: MainViewModel by activityViewModels()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.surveyStep9ImgBack.setOnClickListener {
            findNavController().popBackStack()
        }

        var isCheckedRadio = false

        binding.surveyStep9RadioGroup.setOnCheckedChangeListener { _, checkedId ->
            isCheckedRadio = true
            when (checkedId) {
                R.id.survey_step9_radio_btn1 -> {
                    viewModel.surveyInfo.walkSideToSide = 0
                    binding.surveyStep9RadioBtn1.setCompoundDrawablesWithIntrinsicBounds(
                        R.drawable.btn_radio_on, 0, 0, 0
                    )
                    binding.surveyStep9RadioBtn2.setCompoundDrawablesWithIntrinsicBounds(
                        R.drawable.btn_radio_off, 0, 0, 0
                    )
                    binding.surveyStep9RadioBtn3.setCompoundDrawablesWithIntrinsicBounds(
                        R.drawable.btn_radio_off, 0, 0, 0
                    )
                }

                R.id.survey_step9_radio_btn2 -> {
                    viewModel.surveyInfo.walkSideToSide = 1
                    binding.surveyStep9RadioBtn1.setCompoundDrawablesWithIntrinsicBounds(
                        R.drawable.btn_radio_off, 0, 0, 0
                    )
                    binding.surveyStep9RadioBtn2.setCompoundDrawablesWithIntrinsicBounds(
                        R.drawable.btn_radio_on, 0, 0, 0
                    )
                    binding.surveyStep9RadioBtn3.setCompoundDrawablesWithIntrinsicBounds(
                        R.drawable.btn_radio_off, 0, 0, 0
                    )
                }


                else -> {
                    viewModel.surveyInfo.walkSideToSide = 2
                    binding.surveyStep9RadioBtn1.setCompoundDrawablesWithIntrinsicBounds(
                        R.drawable.btn_radio_off, 0, 0, 0
                    )
                    binding.surveyStep9RadioBtn2.setCompoundDrawablesWithIntrinsicBounds(
                        R.drawable.btn_radio_off, 0, 0, 0
                    )
                    binding.surveyStep9RadioBtn3.setCompoundDrawablesWithIntrinsicBounds(
                        R.drawable.btn_radio_on, 0, 0, 0
                    )
                }
            }
        }

        binding.surveyStep9BtnContinue.setOnClickListener {
            if (isCheckedRadio) findNavController().navigate(R.id.action_surveyStep9Fragment_to_surveyStep10Fragment)
        }
    }
}