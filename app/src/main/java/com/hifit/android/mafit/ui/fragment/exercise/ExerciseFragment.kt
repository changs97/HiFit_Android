package com.hifit.android.mafit.ui.fragment.exercise

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.google.android.material.tabs.TabLayoutMediator
import com.hifit.android.mafit.R
import com.hifit.android.mafit.base.BaseFragment
import com.hifit.android.mafit.data.model.ExerciseItem
import com.hifit.android.mafit.databinding.FragmentExerciseBinding
import com.hifit.android.mafit.test.LivePreviewActivity
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

        viewModel.tryGetBodyInfo()

        binding.exerciseBtnStartExercise.setOnClickListener {
            val intent = Intent(requireContext(), LivePreviewActivity::class.java)
            startActivity(intent)
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

        val sampleData = arrayListOf(
            arrayListOf(
                ExerciseItem(
                    "https://youtu.be/BTJOlvhgbV8"
                ),
                ExerciseItem(
                    "https://youtu.be/-cgWO4JM_pw"
                ),
                ExerciseItem(
                    "https://youtu.be/gXHu0ctL2Ew"
                ),
                ExerciseItem(
                    "https://youtu.be/RsNN049810U"
                ),
                ExerciseItem(
                    "https://youtu.be/1_Vj7_O9I3k"
                )
            )
        )

        val adapter = ExercisePageAdapter(this)
        binding.exercisePager.adapter = adapter
        adapter.submitList(sampleData)

/*        val tabTitles = listOf("전체", "팔", "다리", "가슴", "어깨", "등", "복부", "힙", "전신")

        TabLayoutMediator(binding.exerciseTab, binding.exercisePager) { tab, position ->
            tab.text = tabTitles[position]
        }.attach()*/

        viewModel.navigateNext.observe(viewLifecycleOwner) { event ->
            event.getContentIfNotHandled()?.let {
                if (it) {
                    val videoId = link.substringAfterLast("/")
                    Timber.tag("테스트").d("Video link: $videoId")

                    val action = ExerciseFragmentDirections.actionExerciseFragmentToYoutubePlayerFragment(videoId)
                    findNavController().navigate(action)
                }
            }
        }
    }

    override fun onExerciseClicked(link: String) {
        viewModel.tryPatchStamps()
        this.link = link
    }
}