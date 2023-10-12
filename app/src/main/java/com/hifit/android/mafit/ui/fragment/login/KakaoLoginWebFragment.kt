package com.hifit.android.mafit.ui.fragment.login

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.navigation.fragment.findNavController
import com.hifit.android.mafit.R
import com.hifit.android.mafit.base.BaseFragment
import com.hifit.android.mafit.databinding.FragmentKakaoLoginWebBinding
import timber.log.Timber
import java.net.URISyntaxException


class KakaoLoginWebFragment :
    BaseFragment<FragmentKakaoLoginWebBinding>(R.layout.fragment_kakao_login_web) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.kakaoLoginWeb.settings.run {
            javaScriptEnabled = true
            javaScriptCanOpenWindowsAutomatically = true
            setSupportMultipleWindows(true)
        }

        binding.kakaoLoginWeb.webViewClient = object : WebViewClient() {

            override fun shouldOverrideUrlLoading(
                view: WebView, request: WebResourceRequest
            ): Boolean {
                val uri = request.url
                val code = uri.getQueryParameter("code")

                Timber.i("current uri: $uri")

                if (code != null) {
      /*              val action = KakaoLoginWebFragmentDirections.actionKakaoLoginWebFragmentToLoginFragment(code)
                    findNavController().navigate(action)*/
                } else {
                    Timber.i("code 파라미터를 찾을 수 없습니다.")
                }
                if (request.url.scheme == "intent") {
                    try {
                        val intent =
                            Intent.parseUri(request.url.toString(), Intent.URI_INTENT_SCHEME)

                        if (intent.resolveActivity(requireContext().packageManager) != null) {
                            startActivity(intent)
                            Timber.i("ACTIVITY: ${intent.`package`}")
                            return true
                        }

                        val fallbackUrl = intent.getStringExtra("browser_fallback_url")
                        if (fallbackUrl != null) {
                            view.loadUrl(fallbackUrl)
                            Timber.i("FALLBACK: $fallbackUrl")
                            return true
                        }


                    } catch (e: URISyntaxException) {
                        Timber.e("Invalid intent request", e)
                    }
                }
                return false
            }
        }

        binding.kakaoLoginWeb.loadUrl("https://kauth.kakao.com/oauth/authorize/?response_type=code&client_id=577fc8743ec4ed184bc68b1a6c1bd206&redirect_uri=http://localhost:8080/users/oauth/login")
    }
}