package com.hifit.android.mafit.ui.fragment.survey

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.hifit.android.mafit.R
import com.hifit.android.mafit.base.BaseFragment
import com.hifit.android.mafit.databinding.FragmentSurveyStep11Binding
import com.hifit.android.mafit.viewmodel.MainViewModel


class SurveyStep11Fragment :
    BaseFragment<FragmentSurveyStep11Binding>(R.layout.fragment_survey_step11) {
    private val viewModel: MainViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.vm = viewModel

        viewModel.navigateNext.observe(viewLifecycleOwner) {
            it.getContentIfNotHandled()?.let { next ->
                if (next) {
                    findNavController().navigate(R.id.action_surveyStep11Fragment_to_surveyCompleteFragment)
                }
            }
        }
    }
}