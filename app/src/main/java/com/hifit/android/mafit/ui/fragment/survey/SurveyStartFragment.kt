package com.hifit.android.mafit.ui.fragment.survey

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.hifit.android.mafit.R
import com.hifit.android.mafit.base.BaseFragment
import com.hifit.android.mafit.databinding.FragmentSurveyStartBinding
import com.hifit.android.mafit.viewmodel.MainViewModel


class SurveyStartFragment : BaseFragment<FragmentSurveyStartBinding>(R.layout.fragment_survey_start) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.root.setOnClickListener {
            findNavController().navigate(R.id.action_surveyStartFragment_to_surveyStep1Fragment)
        }
    }
}