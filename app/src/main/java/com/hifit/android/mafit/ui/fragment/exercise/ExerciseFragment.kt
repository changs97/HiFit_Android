package com.hifit.android.mafit.ui.fragment.exercise

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.google.android.material.tabs.TabLayoutMediator
import com.hifit.android.mafit.R
import com.hifit.android.mafit.base.BaseFragment
import com.hifit.android.mafit.data.model.ExerciseItem
import com.hifit.android.mafit.databinding.FragmentExerciseBinding
import com.hifit.android.mafit.ui.MainActivity
import com.hifit.android.mafit.ui.fragment.exercise.adapter.ExerciseAdapterListener
import com.hifit.android.mafit.ui.fragment.exercise.adapter.ExercisePageAdapter
import com.hifit.android.mafit.viewmodel.MainViewModel
import timber.log.Timber

class ExerciseFragment : BaseFragment<FragmentExerciseBinding>(R.layout.fragment_exercise),
    ExerciseAdapterListener {
    private val viewModel: MainViewModel by activityViewModels()
    private lateinit var link: String

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.vm = viewModel
        link = ""

        val lowExerciseList = arrayListOf(
            ExerciseItem(
                "https://youtu.be/RsNN049810U"
            ), ExerciseItem(
                "https://youtu.be/0R1GataJEXA"
            ), ExerciseItem(
                "https://youtu.be/xf_927z_Gys"
            ), ExerciseItem(
                "https://youtu.be/jGKfihuxWIo"
            ), ExerciseItem(
                "https://youtu.be/08UrZHEdfHg"
            ), ExerciseItem(
                "https://youtu.be/BTJOlvhgbV8"
            ), ExerciseItem(
                "https://youtu.be/-cgWO4JM_pw"
            )
        )

        val mediumExerciseList = arrayListOf(
            ExerciseItem(
                "https://youtu.be/gXHu0ctL2Ew"
            ), ExerciseItem(
                "https://youtu.be/7IAC2lgXL7U"
            ), ExerciseItem(
                "https://youtu.be/0PN6LghWX_w"
            ), ExerciseItem(
                "https://youtu.be/TY9O1tDVRW0"
            ), ExerciseItem(
                "https://youtu.be/VbKrvLys1uU"
            ), ExerciseItem(
                "https://youtu.be/joVCXX7wan4"
            )
        )

        val highExerciseList = arrayListOf(
            ExerciseItem(
                "https://youtu.be/HuinxgpBvmM"
            ), ExerciseItem(
                "https://youtu.be/1dgrO_YBBXc"
            ), ExerciseItem(
                "https://youtu.be/OgaGRaBkKxY"
            ), ExerciseItem(
                "https://youtu.be/_D7VLi4cjgU"
            )
        )

        val allExerciseList = arrayListOf<ExerciseItem>()
        allExerciseList.addAll(lowExerciseList)
        allExerciseList.addAll(mediumExerciseList)
        allExerciseList.addAll(highExerciseList)

        viewModel.tryGetBodyInfo()

        binding.exerciseBtnStartExercise.setOnClickListener {
            viewModel.tryGetWorkoutStatus()
        }

        binding.exerciseBtnStartExerciseCertification.setOnClickListener {
            val randomElement = getRandomElementShuffled(allExerciseList)
            if (randomElement != null) {
                onExerciseClicked(randomElement.link)
            } else {
                onExerciseClicked("https://youtu.be/RsNN049810U")
            }
        }

        viewModel.workoutStatus.observe(viewLifecycleOwner) { result ->
            result.getContentIfNotHandled()?.let {
                if (!it.data) findNavController().navigate(R.id.action_exerciseFragment_to_exerciseGuideFragment)
                else showCustomToast("오늘은 포인트를 이미 적립했어요.\n내일 다시 적립하실 수 있어요.")
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


        val adapter = ExercisePageAdapter(this)
        binding.exercisePager.adapter = adapter
        adapter.submitList(
            arrayListOf(
                allExerciseList, lowExerciseList, mediumExerciseList, highExerciseList
            )
        )

        val tabTitles = listOf("전체", "저강도", "중강도", "고강도")

        TabLayoutMediator(binding.exerciseTab, binding.exercisePager) { tab, position ->
            tab.text = tabTitles[position]
        }.attach()

        viewModel.navigateNext.observe(viewLifecycleOwner) { event ->
            event.getContentIfNotHandled()?.let {
                if (it) {
                    val videoId = link.substringAfterLast("/")
                    Timber.tag("테스트").d("Video link: $videoId")

                    val action =
                        ExerciseFragmentDirections.actionExerciseFragmentToYoutubePlayerFragment(
                            videoId
                        )
                    findNavController().navigate(action)
                }
            }
        }
    }

    private fun <T> getRandomElementShuffled(list: ArrayList<T>): T? {
        val shuffledList = list.shuffled()
        return shuffledList.firstOrNull()
    }

    override fun onExerciseClicked(link: String) {
        viewModel.tryPatchStamps()
        this.link = link
    }
}