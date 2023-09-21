package com.hifit.android.mafit.ui.fragment.survey

import android.os.Bundle
import android.view.Gravity
import android.view.View
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.hifit.android.mafit.R
import com.hifit.android.mafit.base.BaseFragment
import com.hifit.android.mafit.databinding.FragmentSurveyStartBinding
import com.hifit.android.mafit.databinding.FragmentSurveyStep3Binding
import com.hifit.android.mafit.viewmodel.MainViewModel


class SurveyStep3Fragment : BaseFragment<FragmentSurveyStep3Binding>(R.layout.fragment_survey_step3) {
    private val viewModel: MainViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.surveyStep3BtnContinue.setOnClickListener  {
            if (binding.surveyStep3Edit.text.isNullOrEmpty()) {
                showCustomToast("키를 입력해주세요")
            } else {
                viewModel.surveyInfo.height = binding.surveyStep3Edit.text.toString().toInt()
                findNavController().navigate(R.id.action_surveyStep3Fragment_to_surveyStep4Fragment)
            }
        }
    }
}