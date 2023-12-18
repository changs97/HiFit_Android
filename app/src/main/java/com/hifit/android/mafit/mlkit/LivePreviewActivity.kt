/*
 * Copyright 2020 Google LLC. All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.hifit.android.mafit.mlkit

import android.animation.Animator
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.CompoundButton
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import com.airbnb.lottie.LottieDrawable
import com.daimajia.androidanimations.library.Techniques
import com.daimajia.androidanimations.library.YoYo
import com.google.android.gms.common.annotation.KeepName
import com.hifit.android.mafit.mlkit.posedetector.PoseDetectorProcessor
import com.hifit.android.mafit.R
import com.hifit.android.mafit.databinding.ActivityLivePreviewBinding
import com.hifit.android.mafit.ui.HomeActivity
import com.hifit.android.mafit.ui.MainActivity
import com.hifit.android.mafit.util.setStatusBarColor
import com.hifit.android.mafit.viewmodel.MainViewModel
import kotlinx.coroutines.launch
import java.io.IOException

/** Live preview demo for ML Kit APIs. */
@KeepName
class LivePreviewActivity : AppCompatActivity(), CompoundButton.OnCheckedChangeListener {
    val binding by lazy { ActivityLivePreviewBinding.inflate(layoutInflater) }
    private val viewModel: MainViewModel by viewModels { MainViewModel.Factory }

    private lateinit var onBackPressedCallback: OnBackPressedCallback

    private var cameraSource: CameraSource? = null

    private var selectedModel = POSE_DETECTION

    private val startListener: () -> Unit = {
        lifecycleScope.launch {
            binding.lotti.addAnimatorListener( object : Animator.AnimatorListener {
                override fun onAnimationStart(animation: Animator) {
                }

                override fun onAnimationEnd(animation: Animator) {
                    binding.lotti.visibility = View.INVISIBLE
                }

                override fun onAnimationCancel(animation: Animator) {
                    binding.lotti.visibility = View.INVISIBLE
                }

                override fun onAnimationRepeat(animation: Animator) {
                }
            })
            binding.lotti.repeatCount = 0
            binding.lotti.visibility = View.VISIBLE
            binding.lotti.playAnimation()
        }
    }

    private val repsChangedListener: (Int) -> Unit = { reps ->
        viewModel.reps.postValue((viewModel.reps.value ?: 0) + 1)

        lifecycleScope.launch {
            YoYo.with(Techniques.Bounce)
                .onStart { binding.livePreviewTxtGood.visibility = View.VISIBLE }
                .onEnd { binding.livePreviewTxtGood.visibility = View.INVISIBLE }
                .duration(1000)
                .repeat(1)
                .playOn(binding.livePreviewTxtGood)

            if ((viewModel.reps.value ?: 0) >= 15) {
                binding.livePreviewPreviewView.stop()
                cameraSource?.release()

                binding.lotti.setAnimation(R.raw.gift)
                binding.lotti.repeatCount = LottieDrawable.INFINITE
                binding.lotti.visibility = View.VISIBLE
                binding.lotti.playAnimation()
                binding.lotti.setOnClickListener {
                    // TODO: 포인트 적립 API 호출
                    binding.lotti.visibility = View.INVISIBLE
                    Toast.makeText(this@LivePreviewActivity, "운동 인증 완료", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this@LivePreviewActivity, HomeActivity::class.java)
                    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    startActivity(intent)
                }
            }
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setStatusBarColor(getColor(R.color.black))

        viewModel.reps.observe(this@LivePreviewActivity) {
            binding.livePreviewProgress.progress = it
            binding.livePreviewTxtReps.text = "${it}/15회"
        }

        onBackPressedCallback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                val intent = Intent(this@LivePreviewActivity, HomeActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(intent)
            }
        }

        onBackPressedDispatcher.addCallback(onBackPressedCallback)

        binding.livePreviewImgBack.setOnClickListener {
            val intent = Intent(this@LivePreviewActivity, HomeActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
        }

    }


    override fun onCheckedChanged(buttonView: CompoundButton, isChecked: Boolean) {
        Log.d(TAG, "Set facing")
        if (cameraSource != null) {
            if (isChecked) {
                cameraSource?.setFacing(CameraSource.CAMERA_FACING_FRONT)
            } else {
                cameraSource?.setFacing(CameraSource.CAMERA_FACING_BACK)
            }
        }
        binding.livePreviewPreviewView.stop()
        startCameraSource()
    }

    private fun createCameraSource(model: String) {
        // If there's no existing cameraSource, create one.
        if (cameraSource == null) {
            cameraSource = CameraSource(this, binding.livePreviewGraphicOverlay)
        }
        try {
            when (model) {

                POSE_DETECTION -> {
                    val poseDetectorOptions =
                        PreferenceUtils.getPoseDetectorOptionsForLivePreview(this)
                    Log.i(TAG, "Using Pose Detector with options $poseDetectorOptions")
                    val shouldShowInFrameLikelihood = false
                    // PreferenceUtils.shouldShowPoseDetectionInFrameLikelihoodLivePreview(this)
                    val visualizeZ = PreferenceUtils.shouldPoseDetectionVisualizeZ(this)
                    val rescaleZ = PreferenceUtils.shouldPoseDetectionRescaleZForVisualization(this)
                    val runClassification = true
                    // PreferenceUtils.shouldPoseDetectionRunClassification(this)
                    cameraSource!!.setMachineLearningFrameProcessor(
                        PoseDetectorProcessor(
                            this,
                            poseDetectorOptions,
                            shouldShowInFrameLikelihood,
                            visualizeZ,
                            rescaleZ,
                            runClassification,/* isStreamMode = */
                            true,
                            repsChangedListener,
                            startListener
                        )
                    )
                }

                else -> Log.e(TAG, "Unknown model: $model")
            }
        } catch (e: Exception) {
            Log.e(TAG, "Can not create image processor: $model", e)
            Toast.makeText(
                applicationContext,
                "Can not create image processor: " + e.message,
                Toast.LENGTH_LONG
            ).show()
        }
    }

    /**
     * Starts or restarts the camera source, if it exists. If the camera source doesn't exist yet
     * (e.g., because onResume was called before the camera source was created), this will be called
     * again when the camera source is created.
     */
    private fun startCameraSource() {
        if (cameraSource != null) {
            try {
                binding.livePreviewPreviewView.start(
                    cameraSource, binding.livePreviewGraphicOverlay
                )
            } catch (e: IOException) {
                Log.e(TAG, "Unable to start camera source.", e)
                cameraSource!!.release()
                cameraSource = null
            }
        }
    }

    public override fun onResume() {
        super.onResume()
        if (viewModel.reps.value!! >= 15) {
            binding.lotti.isVisible = true
            binding.lotti.playAnimation()
        } else {
            createCameraSource(selectedModel)
            startCameraSource()
            binding.lotti.isVisible = false
            binding.lotti.cancelAnimation()
        }
    }

    /** Stops the camera. */
    override fun onPause() {
        super.onPause()
        binding.livePreviewPreviewView.stop()
        binding.lotti.isVisible = false
        binding.lotti.cancelAnimation()
    }

    public override fun onDestroy() {
        super.onDestroy()
        if (cameraSource != null) {
            cameraSource?.release()
        }

        onBackPressedCallback.remove()
    }

    companion object {
        private const val POSE_DETECTION = "Pose Detection"

        private const val TAG = "LivePreviewActivity"
    }
}
