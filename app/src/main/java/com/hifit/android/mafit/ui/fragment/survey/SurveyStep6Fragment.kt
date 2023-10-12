package com.hifit.android.mafit.ui.fragment.survey

import android.os.Bundle
import android.view.View
import android.widget.EditText
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.hifit.android.mafit.R
import com.hifit.android.mafit.base.BaseFragment
import com.hifit.android.mafit.databinding.FragmentSurveyStep6Binding
import com.hifit.android.mafit.viewmodel.MainViewModel


class SurveyStep6Fragment :
    BaseFragment<FragmentSurveyStep6Binding>(R.layout.fragment_survey_step6) {
    private val viewModel: MainViewModel by activityViewModels()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var systolicBloodPressure: Int? = null
        var diastolicBloodPressure: Int? = null
        var heartRate: Int? = null

        binding.surveyStep6ImgBack.setOnClickListener {
            findNavController().popBackStack()
        }

        binding.surveyStep6BtnContinue.setOnClickListener {
            if (systolicBloodPressure != null && diastolicBloodPressure != null && heartRate != null) {
                viewModel.surveyInfo.systolicBloodPressure = systolicBloodPressure!!
                viewModel.surveyInfo.diastolicBloodPressure = diastolicBloodPressure!!
                viewModel.surveyInfo.heartRate = heartRate!!
                findNavController().navigate(R.id.action_surveyStep6Fragment_to_surveyStep7Fragment)
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

        binding.surveyStep6Edit1.addTextChangedListener {
            if (binding.surveyStep6Edit1.isFocused) {
                binding.surveyStep6Edit1.setBackgroundResource(R.drawable.background_stroke_solid_violet5)
            } else {
                if (binding.surveyStep6Edit1.text.isNullOrEmpty()) {
                    binding.surveyStep6Edit1.setBackgroundResource(R.drawable.background_stroke_gray7)
                } else {
                    binding.surveyStep6Edit1.setBackgroundResource(R.drawable.background_radius_10dp_stroke_violet)
                }
            }

            systolicBloodPressure = binding.surveyStep6Edit1.getNullOrTextInt()
        }

        binding.surveyStep6Edit2.addTextChangedListener {
            if (binding.surveyStep6Edit2.isFocused) {
                binding.surveyStep6Edit2.setBackgroundResource(R.drawable.background_stroke_solid_violet5)
            } else {
                if (binding.surveyStep6Edit2.text.isNullOrEmpty()) {
                    binding.surveyStep6Edit2.setBackgroundResource(R.drawable.background_stroke_gray7)
                } else {
                    binding.surveyStep6Edit2.setBackgroundResource(R.drawable.background_radius_10dp_stroke_violet)
                }
            }

            diastolicBloodPressure = binding.surveyStep6Edit2.getNullOrTextInt()
        }

        binding.surveyStep6Edit3.addTextChangedListener {
            if (binding.surveyStep6Edit3.isFocused) {
                binding.surveyStep6Edit3.setBackgroundResource(R.drawable.background_stroke_solid_violet5)
            } else {
                if (binding.surveyStep6Edit3.text.isNullOrEmpty()) {
                    binding.surveyStep6Edit3.setBackgroundResource(R.drawable.background_stroke_gray7)
                } else {
                    binding.surveyStep6Edit3.setBackgroundResource(R.drawable.background_radius_10dp_stroke_violet)
                }
            }

            heartRate = binding.surveyStep6Edit3.getNullOrTextInt()
        }
    }

    private fun EditText.getNullOrTextInt(): Int? {
        return if (this.text.isNullOrBlank()) null else this.text.toString().toInt()
    }
}