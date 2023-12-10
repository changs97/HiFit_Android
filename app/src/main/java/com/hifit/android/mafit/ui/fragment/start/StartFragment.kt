package com.hifit.android.mafit.ui.fragment.start

import android.content.Intent
import android.content.Intent.FLAG_ACTIVITY_CLEAR_TASK
import android.content.Intent.FLAG_ACTIVITY_NEW_TASK
import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.hifit.android.mafit.HiFitApplication
import com.hifit.android.mafit.R
import com.hifit.android.mafit.base.BaseFragment
import com.hifit.android.mafit.data.source.local.UserInfo
import com.hifit.android.mafit.databinding.FragmentStartBinding
import com.hifit.android.mafit.ui.HomeActivity
import com.hifit.android.mafit.ui.MainActivity
import com.hifit.android.mafit.viewmodel.MainViewModel
import com.kakao.sdk.user.UserApiClient
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import timber.log.Timber

class StartFragment : BaseFragment<FragmentStartBinding>(R.layout.fragment_start) {
    private val viewModel: MainViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewLifecycleOwner.lifecycleScope.launch {
            delay(1000)
            viewModel.getToken()?.let {
                viewModel.tryGetBodyInfo()
            } ?: run {
                findNavController().navigate(R.id.action_startFragment_to_loginFragment)
            }
        }

        viewModel.errorEvent.observe(viewLifecycleOwner) { event ->
            event.getContentIfNotHandled()?.let {
                if (it == 40103) {
                    findNavController().navigate(R.id.action_startFragment_to_loginFragment)
                }
            }
        }

        viewModel.bodyInfo.observe(viewLifecycleOwner) { bodyInfo ->
            if (bodyInfo.currentBmi != null) {
                val intent = Intent(requireContext(), HomeActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(intent)
            } else {
                findNavController().navigate(R.id.action_loginFragment_to_survey_graph)
            }
        }

    }

}