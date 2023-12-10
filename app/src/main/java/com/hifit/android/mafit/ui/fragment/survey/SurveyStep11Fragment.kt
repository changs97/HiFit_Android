package com.hifit.android.mafit.ui.fragment.survey

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.hifit.android.mafit.R
import com.hifit.android.mafit.base.BaseFragment
import com.hifit.android.mafit.databinding.FragmentSurveyStep11Binding
import com.hifit.android.mafit.ui.HomeActivity
import com.hifit.android.mafit.ui.MainActivity
import com.hifit.android.mafit.viewmodel.MainViewModel


class SurveyStep11Fragment :
    BaseFragment<FragmentSurveyStep11Binding>(R.layout.fragment_survey_step11) {
    private val viewModel: MainViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.vm = viewModel

        binding.surveyStep11ImgBack.setOnClickListener {
            findNavController().popBackStack()
        }

        var isCheckedRadio = false

        binding.surveyStep11RadioGroup.setOnCheckedChangeListener { _, checkedId ->
            isCheckedRadio = true
            when (checkedId) {
                R.id.survey_step11_radio_btn1 -> {
                    viewModel.surveyInfo.stairs = 0
                    binding.surveyStep11RadioBtn1.setCompoundDrawablesWithIntrinsicBounds(
                        R.drawable.btn_radio_on, 0, 0, 0
                    )
                    binding.surveyStep11RadioBtn2.setCompoundDrawablesWithIntrinsicBounds(
                        R.drawable.btn_radio_off, 0, 0, 0
                    )
                    binding.surveyStep11RadioBtn3.setCompoundDrawablesWithIntrinsicBounds(
                        R.drawable.btn_radio_off, 0, 0, 0
                    )
                }

                R.id.survey_step11_radio_btn2 -> {
                    viewModel.surveyInfo.stairs = 1
                    binding.surveyStep11RadioBtn1.setCompoundDrawablesWithIntrinsicBounds(
                        R.drawable.btn_radio_off, 0, 0, 0
                    )
                    binding.surveyStep11RadioBtn2.setCompoundDrawablesWithIntrinsicBounds(
                        R.drawable.btn_radio_on, 0, 0, 0
                    )
                    binding.surveyStep11RadioBtn3.setCompoundDrawablesWithIntrinsicBounds(
                        R.drawable.btn_radio_off, 0, 0, 0
                    )
                }


                else -> {
                    viewModel.surveyInfo.stairs = 2
                    binding.surveyStep11RadioBtn1.setCompoundDrawablesWithIntrinsicBounds(
                        R.drawable.btn_radio_off, 0, 0, 0
                    )
                    binding.surveyStep11RadioBtn2.setCompoundDrawablesWithIntrinsicBounds(
                        R.drawable.btn_radio_off, 0, 0, 0
                    )
                    binding.surveyStep11RadioBtn3.setCompoundDrawablesWithIntrinsicBounds(
                        R.drawable.btn_radio_on, 0, 0, 0
                    )
                }
            }
        }

        viewModel.showToast.observe(viewLifecycleOwner) { event ->
            event.getContentIfNotHandled()?.let {
                if (it.isNotEmpty()) showCustomToast(it)
            }
        }

        viewModel.errorEvent.observe(viewLifecycleOwner) { event ->
            event.getContentIfNotHandled()?.let {
                if (it == 40103) {
                    val intent = Intent(requireContext(), MainActivity::class.java)
                    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    startActivity(intent)
                }
            }
        }


        viewModel.navigateNext.observe(viewLifecycleOwner) {
            it.getContentIfNotHandled()?.let { next ->
                if (next && isCheckedRadio) {
                    findNavController().navigate(R.id.action_surveyStep11Fragment_to_surveyCompleteFragment)
                }
            }
        }
    }
}