package com.hifit.android.mafit.ui.fragment.survey

import android.os.Bundle
import android.view.View
import android.widget.EditText
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.hifit.android.mafit.R
import com.hifit.android.mafit.base.BaseFragment
import com.hifit.android.mafit.databinding.FragmentSurveyStep5Binding
import com.hifit.android.mafit.viewmodel.MainViewModel


class SurveyStep5Fragment :
    BaseFragment<FragmentSurveyStep5Binding>(R.layout.fragment_survey_step5) {
    private val viewModel: MainViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var systolicBloodPressure: Int? = null
        var diastolicBloodPressure: Int? = null
        var heartRate: Int? = null

        binding.surveyStep5BtnContinue.setOnClickListener {
            if (systolicBloodPressure != null && diastolicBloodPressure != null && heartRate != null) {
                viewModel.surveyInfo.systolicBloodPressure = systolicBloodPressure!!
                viewModel.surveyInfo.diastolicBloodPressure = diastolicBloodPressure!!
                viewModel.surveyInfo.heartRate = heartRate!!
                findNavController().navigate(R.id.action_surveyStep5Fragment_to_surveyStep6Fragment)
            } else {
                if (systolicBloodPressure == null) {
                    showCustomToast("수축기 혈압을 입력해주세요")
                } else if (diastolicBloodPressure == null) {
                    showCustomToast("이완기 혈압을 입력해주세요")
                } else if (heartRate == null) {
                    showCustomToast("15초 맥박 수를 입력해주세요")
                }
            }
        }

        binding.surveyStep5Edit1.addTextChangedListener {
            systolicBloodPressure = binding.surveyStep5Edit1.getNullOrTextInt()
        }

        binding.surveyStep5Edit2.addTextChangedListener {
            diastolicBloodPressure = binding.surveyStep5Edit2.getNullOrTextInt()
        }

        binding.surveyStep5Edit3.addTextChangedListener {
            heartRate = binding.surveyStep5Edit3.getNullOrTextInt()
        }
    }

    private fun EditText.getNullOrTextInt(): Int? {
        return if (this.text.isNullOrBlank()) null else this.text.toString().toInt()
    }
}