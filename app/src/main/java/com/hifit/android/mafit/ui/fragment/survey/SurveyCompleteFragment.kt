package com.hifit.android.mafit.ui.fragment.survey

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.hifit.android.mafit.R
import com.hifit.android.mafit.base.BaseFragment
import com.hifit.android.mafit.data.source.network.SurveyRetrofitInterface
import com.hifit.android.mafit.databinding.FragmentSurveyCompleteBinding
import com.hifit.android.mafit.ui.MainActivity
import com.hifit.android.mafit.viewmodel.MainViewModel
import kotlinx.coroutines.launch
import timber.log.Timber
import java.lang.Exception

class SurveyCompleteFragment :
    BaseFragment<FragmentSurveyCompleteBinding>(R.layout.fragment_survey_complete) {
    private val viewModel: MainViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.vm = viewModel

        viewModel.tryGetUserInfo()

        viewModel.userInfo.observe(viewLifecycleOwner) { userInfo ->
            Timber.tag("테스트").d("$userInfo")
            userInfo?.let {
                binding.surveyCompleteTxtName.text = it.name
                binding.surveyCompleteTxtAge.text = it.age.toString()
                binding.surveyCompleteTxtSex.text = when (it.gender) {
                    "MALE" -> "남성"
                    "FEMALE" -> "여성"
                    else -> ""
                }
            }
        }

        binding.surveyCompleteBtnRecommend.setOnClickListener {
            findNavController().navigate(R.id.action_surveyCompleteFragment_to_homeFragment)
        }
    }
}