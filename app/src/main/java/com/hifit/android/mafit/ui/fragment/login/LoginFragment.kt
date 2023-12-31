package com.hifit.android.mafit.ui.fragment.login

import android.content.Intent
import android.graphics.Typeface
import android.os.Bundle

import android.view.View
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.hifit.android.mafit.HiFitApplication
import com.hifit.android.mafit.R
import com.hifit.android.mafit.base.BaseFragment
import com.hifit.android.mafit.data.model.login.LoginRequestBody
import com.hifit.android.mafit.data.source.network.LoginRetrofitInterface
import com.hifit.android.mafit.databinding.FragmentLoginBinding
import com.hifit.android.mafit.ui.HomeActivity
import com.hifit.android.mafit.ui.MainActivity
import com.hifit.android.mafit.util.Constant.Companion.X_ACCESS_TOKEN
import com.hifit.android.mafit.viewmodel.MainViewModel
import com.kakao.sdk.auth.model.OAuthToken
import com.kakao.sdk.common.model.ClientError
import com.kakao.sdk.common.model.ClientErrorCause
import com.kakao.sdk.user.UserApiClient
import kotlinx.coroutines.launch
import timber.log.Timber
import java.lang.Exception


class LoginFragment : BaseFragment<FragmentLoginBinding>(R.layout.fragment_login) {
    private val viewModel: MainViewModel by activityViewModels()
    private val args: LoginFragmentArgs by navArgs()

    private val mCallback: (OAuthToken?, Throwable?) -> Unit = { token, error ->
        if (error != null) {
            Timber.e("로그인 실패 $error")
        } else if (token != null) {
            Timber.d("로그인 성공 ${token.accessToken}")
            viewModel.tryPostLogin(token.accessToken)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.navigateNext.observe(viewLifecycleOwner) { event ->
            event.getContentIfNotHandled()?.let {
                viewModel.tryGetBodyInfo()
            }
        }

        binding.loginImg.setOnClickListener {
            kakaoLogout()
        }

        binding.button.setOnClickListener {
            viewModel.getToken()?.let {
                viewModel.tryGetBodyInfo()
            } ?: run {
                kakaoLogin()
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


    private fun kakaoLogout() {
        UserApiClient.instance.unlink { error ->
            if (error != null) {
                Timber.e("연결 끊기 실패", error)
            } else {
                Timber.i("연결 끊기 성공")
                viewLifecycleOwner.lifecycleScope.launch {
                    viewModel.deleteUserInfo()
                    activity?.finish()
                }
            }
        }
    }

    private fun kakaoLogin() {
        if (UserApiClient.instance.isKakaoTalkLoginAvailable(requireContext())) {
            // 카카오톡 로그인
            UserApiClient.instance.loginWithKakaoTalk(requireContext()) { token, error ->
                // 로그인 실패 부분
                if (error != null) {
                    Timber.e("로그인 실패 $error")
                    // 사용자가 취소
                    if (error is ClientError && error.reason == ClientErrorCause.Cancelled) {
                        return@loginWithKakaoTalk
                    }
                    // 다른 오류
                    else {
                        UserApiClient.instance.loginWithKakaoAccount(
                            requireContext(), callback = mCallback
                        ) // 카카오 이메일 로그인
                    }
                }
                // 로그인 성공 부분
                else if (token != null) {
                    Timber.d("로그인 성공 ${token.accessToken}")
                    viewModel.tryPostLogin(token.accessToken)
                }
            }
        } else { // 카카오 이메일 로그인
            UserApiClient.instance.loginWithKakaoAccount(
                requireContext(), callback = mCallback
            )
        }
    }
}