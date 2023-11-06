package com.hifit.android.mafit.ui.fragment.home

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.hifit.android.mafit.R
import com.hifit.android.mafit.base.BaseFragment
import com.hifit.android.mafit.databinding.FragmentHomeBinding
import com.hifit.android.mafit.ui.HomeActivity
import com.hifit.android.mafit.viewmodel.MainViewModel
import timber.log.Timber


class HomeFragment : BaseFragment<FragmentHomeBinding>(R.layout.fragment_home) {
    private val viewModel: MainViewModel by activityViewModels()
    private lateinit var onBackPressedCallback: OnBackPressedCallback
    private var clickTime: Long = 0

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.vm = viewModel

        clickTime = 0
        onBackPressedCallback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                if(System.currentTimeMillis() - clickTime >= 2000) {
                    clickTime = System.currentTimeMillis()
                    showCustomToast("'뒤로가기' 버튼을 한번 더 누르시면 앱이 종료됩니다.")
                } else {
                    requireActivity().finish()
                }
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(onBackPressedCallback)

        with(viewModel) {
            tryGetUserInfo()
            tryGetBodyInfo()
            tryGetDiet()
            tryGetWorkoutInfo()
        }

        viewModel.showToast.observe(viewLifecycleOwner) { event ->
            event.getContentIfNotHandled()?.let {
                if (it.isNotEmpty()) showCustomToast(it)
            }
        }

        viewModel.errorEvent.observe(viewLifecycleOwner) { event ->
            event.getContentIfNotHandled()?.let {
                if (it == 40103) activity?.finish()
            }
        }

        viewModel.bodyInfo.observe(viewLifecycleOwner) { bodyInfo ->
            with(binding) {
                homeTxtBmi1.visibility = View.INVISIBLE
                homeTxtBmi2.visibility = View.INVISIBLE
                homeTxtBmi3.visibility = View.INVISIBLE
                homeTxtBmi4.visibility = View.INVISIBLE
                homeTxtBmi5.visibility = View.INVISIBLE
                homeTxtBmi6.visibility = View.INVISIBLE
                homeTxtBmi7.visibility = View.INVISIBLE
                homeTxtBmi8.visibility = View.INVISIBLE
                homeTxtBmi9.visibility = View.INVISIBLE
                homeTxtBmi10.visibility = View.INVISIBLE
                homeTxtBmi11.visibility = View.INVISIBLE
                homeTxtBmi12.visibility = View.INVISIBLE
                homeTxtBmi13.visibility = View.INVISIBLE
                homeTxtBmi14.visibility = View.INVISIBLE
                homeTxtBmi15.visibility = View.INVISIBLE
                homeTxtBmi16.visibility = View.INVISIBLE
                homeTxtBmi17.visibility = View.INVISIBLE
                homeTxtBmi18.visibility = View.INVISIBLE
                homeTxtBmi19.visibility = View.INVISIBLE
                homeTxtBmi20.visibility = View.INVISIBLE
                homeTxtBmi21.visibility = View.INVISIBLE
                homeTxtBmi22.visibility = View.INVISIBLE
                homeTxtBmi23.visibility = View.INVISIBLE
                homeTxtBmi24.visibility = View.INVISIBLE
                homeTxtBmi25.visibility = View.INVISIBLE
            }

            bodyInfo?.currentBmi?.let {
                val bmi = String.format("%.1f", it)
                // 각 범위 5등분 (가장 왼쪽/중앙에서 살짝 왼쪽/중앙/중앙에서 살짝 오른쪽/가장 오른쪽)
                val bmiName = if (it <= 18.5) {
                    when (it) {
                        in 0.0..5.0 -> {
                            binding.homeTxtBmi1.visibility = View.VISIBLE
                            binding.homeTxtBmi1.text = bmi
                        }
                        in 5.0..8.0 -> {
                            binding.homeTxtBmi2.visibility = View.VISIBLE
                            binding.homeTxtBmi2.text = bmi
                        }
                        in 8.0..9.5 -> {
                            binding.homeTxtBmi3.visibility = View.VISIBLE
                            binding.homeTxtBmi3.text = bmi
                        }
                        in 9.5..18.0 -> {
                            binding.homeTxtBmi4.visibility = View.VISIBLE
                            binding.homeTxtBmi4.text = bmi
                        }
                        in 18.0..18.5 -> {
                            binding.homeTxtBmi5.visibility = View.VISIBLE
                            binding.homeTxtBmi5.text = bmi
                        }
                    }
                    "저체중"
                } else if (it > 18.5 && it <= 22.9) {
                    when (it) {
                        in 18.5..19.5 -> {
                            binding.homeTxtBmi6.visibility = View.VISIBLE
                            binding.homeTxtBmi6.text = bmi
                        }
                        in 19.5..20.5 -> {
                            binding.homeTxtBmi7.visibility = View.VISIBLE
                            binding.homeTxtBmi7.text = bmi
                        }
                        in 20.5..21.5 -> {
                            binding.homeTxtBmi8.visibility = View.VISIBLE
                            binding.homeTxtBmi8.text = bmi
                        }
                        in 21.5..22.5 -> {
                            binding.homeTxtBmi9.visibility = View.VISIBLE
                            binding.homeTxtBmi9.text = bmi
                        }
                        in 22.5..22.9 -> {
                            binding.homeTxtBmi10.visibility = View.VISIBLE
                            binding.homeTxtBmi10.text = bmi
                        }
                    }
                    "정상체중"
                } else if (it in 23.0..24.9) {
                    when (it) {
                        in 23.0..23.5 -> {
                            binding.homeTxtBmi11.visibility = View.VISIBLE
                            binding.homeTxtBmi11.text = bmi
                        }
                        in 23.5..24.0 -> {
                            binding.homeTxtBmi12.visibility = View.VISIBLE
                            binding.homeTxtBmi12.text = bmi
                        }
                        in 24.0..24.5 -> {
                            binding.homeTxtBmi13.visibility = View.VISIBLE
                            binding.homeTxtBmi13.text = bmi
                        }
                        in 24.5..24.8 -> {
                            binding.homeTxtBmi14.visibility = View.VISIBLE
                            binding.homeTxtBmi14.text = bmi
                        }
                        in 24.8..24.9 -> {
                            binding.homeTxtBmi15.visibility = View.VISIBLE
                            binding.homeTxtBmi15.text = bmi
                        }
                    }
                    "과체중"
                } else if (it in 25.0..29.9) {
                    when (it) {
                        in 25.0..25.5 -> {
                            binding.homeTxtBmi16.visibility = View.VISIBLE
                            binding.homeTxtBmi16.text = bmi
                        }
                        in 25.5..26.0 -> {
                            binding.homeTxtBmi17.visibility = View.VISIBLE
                            binding.homeTxtBmi17.text = bmi
                        }
                        in 26.0..28.0 -> {
                            binding.homeTxtBmi18.visibility = View.VISIBLE
                            binding.homeTxtBmi18.text = bmi
                        }
                        in 28.0..29.0 -> {
                            binding.homeTxtBmi19.visibility = View.VISIBLE
                            binding.homeTxtBmi19.text = bmi
                        }
                        in 29.0..29.9 -> {
                            binding.homeTxtBmi20.visibility = View.VISIBLE
                            binding.homeTxtBmi20.text = bmi
                        }
                    }
                    "비만"
                } else {
                    when (it) {
                        in 29.9..32.0 -> {
                            binding.homeTxtBmi21.visibility = View.VISIBLE
                            binding.homeTxtBmi21.text = bmi
                        }
                        in 32.0..35.0 -> {
                            binding.homeTxtBmi22.visibility = View.VISIBLE
                            binding.homeTxtBmi22.text = bmi
                        }
                        in 35.0..38.0 -> {
                            binding.homeTxtBmi23.visibility = View.VISIBLE
                            binding.homeTxtBmi23.text = bmi
                        }
                        in 38.0..40.0 -> {
                            binding.homeTxtBmi24.visibility = View.VISIBLE
                            binding.homeTxtBmi24.text = bmi
                        }
                        else -> {
                            binding.homeTxtBmi25.visibility = View.VISIBLE
                            binding.homeTxtBmi25.text = bmi
                        }
                    }
                    "고도비만"
                }



                binding.homeTxtBmi.text = "$bmi $bmiName"
            }
        }

        viewModel.userInfo.observe(viewLifecycleOwner) { userInfo ->
            Timber.tag("테스트").d("$userInfo")
            userInfo?.let {
                var sarcopeniaName = ""
                when (userInfo.sarcopenia) {
                    "LOW" -> {
                        sarcopeniaName = "저위험군"
                    }

                    "MEDIUM" -> {
                        sarcopeniaName = "중위험군"
                    }

                    "HIGH" -> {
                        sarcopeniaName = "고위험군"
                    }

                    else -> {
                        // TODO: 빈 화면 또는 에러 화면 표시
                    }
                }
                binding.homeTxtResultType.text = sarcopeniaName
            }
        }

        binding.homeLlCoinContainer.setOnClickListener {
            (requireActivity() as HomeActivity).selectedItemId(R.id.productFragment)
        }

        binding.homeBtnStamp.setOnClickListener {
            (requireActivity() as HomeActivity).selectedItemId(R.id.exerciseFragment)
        }

        binding.homeBtnResult.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_surveyCompleteFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        onBackPressedCallback.remove()
    }
}