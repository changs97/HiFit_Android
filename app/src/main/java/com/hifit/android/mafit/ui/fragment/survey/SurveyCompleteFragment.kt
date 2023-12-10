package com.hifit.android.mafit.ui.fragment.survey

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.hifit.android.mafit.R
import com.hifit.android.mafit.base.BaseFragment
import com.hifit.android.mafit.databinding.FragmentSurveyCompleteBinding
import com.hifit.android.mafit.ui.HomeActivity
import com.hifit.android.mafit.ui.MainActivity
import com.hifit.android.mafit.viewmodel.MainViewModel
import timber.log.Timber
import java.text.DecimalFormat

class SurveyCompleteFragment :
    BaseFragment<FragmentSurveyCompleteBinding>(R.layout.fragment_survey_complete) {
    private val viewModel: MainViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.vm = viewModel

        viewModel.tryGetUserInfo()
        viewModel.tryGetDiet()
        viewModel.tryGetBodyInfo()

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

        viewModel.bodyInfo.observe(viewLifecycleOwner) { bodyInfo ->
            with(binding) {
                surveyCompleteTxtBmi1.visibility = INVISIBLE
                surveyCompleteTxtBmi2.visibility = INVISIBLE
                surveyCompleteTxtBmi3.visibility = INVISIBLE
                surveyCompleteTxtBmi4.visibility = INVISIBLE
                surveyCompleteTxtBmi5.visibility = INVISIBLE
                surveyCompleteTxtBmi6.visibility = INVISIBLE
                surveyCompleteTxtBmi7.visibility = INVISIBLE
                surveyCompleteTxtBmi8.visibility = INVISIBLE
                surveyCompleteTxtBmi9.visibility = INVISIBLE
                surveyCompleteTxtBmi10.visibility = INVISIBLE
                surveyCompleteTxtBmi11.visibility = INVISIBLE
                surveyCompleteTxtBmi12.visibility = INVISIBLE
                surveyCompleteTxtBmi13.visibility = INVISIBLE
                surveyCompleteTxtBmi14.visibility = INVISIBLE
                surveyCompleteTxtBmi15.visibility = INVISIBLE
                surveyCompleteTxtBmi16.visibility = INVISIBLE
                surveyCompleteTxtBmi17.visibility = INVISIBLE
                surveyCompleteTxtBmi18.visibility = INVISIBLE
                surveyCompleteTxtBmi19.visibility = INVISIBLE
                surveyCompleteTxtBmi20.visibility = INVISIBLE
                surveyCompleteTxtBmi21.visibility = INVISIBLE
                surveyCompleteTxtBmi22.visibility = INVISIBLE
                surveyCompleteTxtBmi23.visibility = INVISIBLE
                surveyCompleteTxtBmi24.visibility = INVISIBLE
                surveyCompleteTxtBmi25.visibility = INVISIBLE
            }

            bodyInfo?.currentBmi?.let {
                val bmi = String.format("%.1f", it)
                // 각 범위 5등분 (가장 왼쪽/중앙에서 살짝 왼쪽/중앙/중앙에서 살짝 오른쪽/가장 오른쪽)
                val bmiName = if (it <= 18.5) {
                    when (it) {
                        in 0.0..5.0 -> {
                            binding.surveyCompleteTxtBmi1.visibility = VISIBLE
                            binding.surveyCompleteTxtBmi1.text = bmi
                        }
                        in 5.0..8.0 -> {
                            binding.surveyCompleteTxtBmi2.visibility = VISIBLE
                            binding.surveyCompleteTxtBmi2.text = bmi
                        }
                        in 8.0..9.5 -> {
                            binding.surveyCompleteTxtBmi3.visibility = VISIBLE
                            binding.surveyCompleteTxtBmi3.text = bmi
                        }
                        in 9.5..18.0 -> {
                            binding.surveyCompleteTxtBmi4.visibility = VISIBLE
                            binding.surveyCompleteTxtBmi4.text = bmi
                        }
                        in 18.0..18.5 -> {
                            binding.surveyCompleteTxtBmi5.visibility = VISIBLE
                            binding.surveyCompleteTxtBmi5.text = bmi
                        }
                    }
                    "저체중"
                } else if (it > 18.5 && it <= 22.9) {
                    when (it) {
                        in 18.5..19.5 -> {
                            binding.surveyCompleteTxtBmi6.visibility = VISIBLE
                            binding.surveyCompleteTxtBmi6.text = bmi
                        }
                        in 19.5..20.5 -> {
                            binding.surveyCompleteTxtBmi7.visibility = VISIBLE
                            binding.surveyCompleteTxtBmi7.text = bmi
                        }
                        in 20.5..21.5 -> {
                            binding.surveyCompleteTxtBmi8.visibility = VISIBLE
                            binding.surveyCompleteTxtBmi8.text = bmi
                        }
                        in 21.5..22.5 -> {
                            binding.surveyCompleteTxtBmi9.visibility = VISIBLE
                            binding.surveyCompleteTxtBmi9.text = bmi
                        }
                        in 22.5..22.9 -> {
                            binding.surveyCompleteTxtBmi10.visibility = VISIBLE
                            binding.surveyCompleteTxtBmi10.text = bmi
                        }
                    }
                    "정상체중"
                } else if (it in 23.0..24.9) {
                    when (it) {
                        in 23.0..23.5 -> {
                            binding.surveyCompleteTxtBmi11.visibility = VISIBLE
                            binding.surveyCompleteTxtBmi11.text = bmi
                        }
                        in 23.5..24.0 -> {
                            binding.surveyCompleteTxtBmi12.visibility = VISIBLE
                            binding.surveyCompleteTxtBmi12.text = bmi
                        }
                        in 24.0..24.5 -> {
                            binding.surveyCompleteTxtBmi13.visibility = VISIBLE
                            binding.surveyCompleteTxtBmi13.text = bmi
                        }
                        in 24.5..24.8 -> {
                            binding.surveyCompleteTxtBmi14.visibility = VISIBLE
                            binding.surveyCompleteTxtBmi14.text = bmi
                        }
                        in 24.8..24.9 -> {
                            binding.surveyCompleteTxtBmi15.visibility = VISIBLE
                            binding.surveyCompleteTxtBmi15.text = bmi
                        }
                    }
                    "과체중"
                } else if (it in 25.0..29.9) {
                    when (it) {
                        in 25.0..25.5 -> {
                            binding.surveyCompleteTxtBmi16.visibility = VISIBLE
                            binding.surveyCompleteTxtBmi16.text = bmi
                        }
                        in 25.5..26.0 -> {
                            binding.surveyCompleteTxtBmi17.visibility = VISIBLE
                            binding.surveyCompleteTxtBmi17.text = bmi
                        }
                        in 26.0..28.0 -> {
                            binding.surveyCompleteTxtBmi18.visibility = VISIBLE
                            binding.surveyCompleteTxtBmi18.text = bmi
                        }
                        in 28.0..29.0 -> {
                            binding.surveyCompleteTxtBmi19.visibility = VISIBLE
                            binding.surveyCompleteTxtBmi19.text = bmi
                        }
                        in 29.0..29.9 -> {
                            binding.surveyCompleteTxtBmi20.visibility = VISIBLE
                            binding.surveyCompleteTxtBmi20.text = bmi
                        }
                    }
                    "비만"
                } else {
                    when (it) {
                        in 29.9..32.0 -> {
                            binding.surveyCompleteTxtBmi21.visibility = VISIBLE
                            binding.surveyCompleteTxtBmi21.text = bmi
                        }
                        in 32.0..35.0 -> {
                            binding.surveyCompleteTxtBmi22.visibility = VISIBLE
                            binding.surveyCompleteTxtBmi22.text = bmi
                        }
                        in 35.0..38.0 -> {
                            binding.surveyCompleteTxtBmi23.visibility = VISIBLE
                            binding.surveyCompleteTxtBmi23.text = bmi
                        }
                        in 38.0..40.0 -> {
                            binding.surveyCompleteTxtBmi24.visibility = VISIBLE
                            binding.surveyCompleteTxtBmi24.text = bmi
                        }
                        else -> {
                            binding.surveyCompleteTxtBmi25.visibility = VISIBLE
                            binding.surveyCompleteTxtBmi25.text = bmi
                        }
                    }
                    "고도비만"
                }


                binding.surveyCompleteTxtBmi.text = "$bmi $bmiName"
            }
        }

        viewModel.userInfo.observe(viewLifecycleOwner) { userInfo ->
            Timber.tag("테스트").d("$userInfo")
            userInfo?.let {
                val height = it.height
                val weight = it.weight

                if (height != null && weight != null) {
                    val standardWeight = (height - 100) * 0.9
                    val recommendedCalorie = standardWeight * 30

                    binding.surveyCompleteTxtContent5Kcal.text =
                        DecimalFormat("#,###").format(recommendedCalorie.toInt())

                    val proteinIntake = weight * 1.2

                    binding.surveyCompleteTxtContent6Kcal.text =
                        DecimalFormat("#,###").format(proteinIntake.toInt()) + "g"

                    val egg = (proteinIntake / 9).toInt()
                    val tofu = (proteinIntake / 9).toInt()
                    val chicken =  (proteinIntake / 20).toInt()
                    binding.surveyCompleteTxtGuide.text = getString(R.string.nutrition_intake_guide).format(chicken, egg, tofu)


                    binding.surveyCompleteTxtContent5Intake.text =
                        DecimalFormat("#,###").format(proteinIntake.toInt())
                }
                var imgResourceId: Int? = null
                var sarcopeniaName = ""
                var contents = ""
                var textColor = resources.getColor(R.color.yellow, null)
                when (userInfo.sarcopenia) {
                    "LOW" -> {
                        imgResourceId = R.drawable.ic_square_mint
                        sarcopeniaName = "저위험군"
                        textColor = resources.getColor(R.color.green2, null)
                        contents = getString(R.string.survey_complete_contents1)
                    }

                    "MEDIUM" -> {
                        imgResourceId = R.drawable.ic_square_yellow
                        sarcopeniaName = "중위험군"
                        textColor = resources.getColor(R.color.yellow2, null)
                        contents = getString(R.string.survey_complete_contents2)
                    }

                    "HIGH" -> {
                        imgResourceId = R.drawable.ic_square_red
                        sarcopeniaName = "고위험군"
                        textColor = resources.getColor(R.color.red2, null)
                        contents = getString(R.string.survey_complete_contents3)
                    }

                    else -> {
                        // TODO: 빈 화면 또는 에러 화면 표시
                    }
                }

                Glide.with(this).load(imgResourceId).centerCrop()
                    .into(binding.surveyCompleteImgResult)
                binding.surveyCompleteTxtResultType.text = sarcopeniaName
                binding.surveyCompleteTxtResultType2.text = sarcopeniaName
                binding.surveyCompleteTxtResultType2.setTextColor(textColor)
                binding.surveyCompleteTxtContents.text = contents

                binding.surveyCompleteTxtName.text = it.name
                binding.surveyCompleteTxtAge.text = it.age.toString()
                binding.surveyCompleteTxtGender.text = when (it.gender) {
                    "MALE" -> "남성"
                    "FEMALE" -> "여성"
                    else -> ""
                }
            }
        }

        binding.surveyCompleteBtnRecommend.setOnClickListener {
            val intent = Intent(requireContext(), HomeActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
        }
    }
}