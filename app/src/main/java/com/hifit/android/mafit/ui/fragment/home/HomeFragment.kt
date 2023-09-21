package com.hifit.android.mafit.ui.fragment.home

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.hifit.android.mafit.R
import com.hifit.android.mafit.base.BaseFragment
import com.hifit.android.mafit.databinding.FragmentHomeBinding
import com.hifit.android.mafit.databinding.FragmentSurveyCompleteBinding

class HomeFragment : BaseFragment<FragmentHomeBinding>(R.layout.fragment_home) {
    private var testProgress = 0

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.homeBtnStamp.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_exerciseFragment)
        }

        binding.progressBar.max = 10
        binding.progressBar.progress = 0
        binding.progressBar.setOnClickListener {
            binding.progressBar.progress = ++testProgress
        }
    }
}