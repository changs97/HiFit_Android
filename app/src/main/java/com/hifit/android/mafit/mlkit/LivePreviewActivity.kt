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

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.util.Log
import android.view.View
import android.widget.CompoundButton
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.daimajia.androidanimations.library.Techniques
import com.daimajia.androidanimations.library.YoYo
import com.google.android.gms.common.annotation.KeepName
import com.hifit.android.mafit.mlkit.posedetector.PoseDetectorProcessor
import com.hifit.android.mafit.R
import com.hifit.android.mafit.databinding.ActivityLivePreviewBinding
import com.hifit.android.mafit.util.setStatusBarColor
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView
import kotlinx.coroutines.launch
import java.io.IOException

/** Live preview demo for ML Kit APIs. */
@KeepName
class LivePreviewActivity : AppCompatActivity(), CompoundButton.OnCheckedChangeListener {
    val binding by lazy { ActivityLivePreviewBinding.inflate(layoutInflater) }
    private var cameraSource: CameraSource? = null

    private var selectedModel = POSE_DETECTION

    private var reps: Int = 0
    private var repsChangedListener: (Int) -> Unit = { reps ->
        this.reps = reps

        if (reps >= 15) {
            binding.livePreviewPreviewView.stop()
            cameraSource?.release()
        }

        lifecycleScope.launch {
            binding.livePreviewProgress.progress = reps
            binding.livePreviewTxtReps.text = "${reps}/15회"

            YoYo.with(Techniques.Bounce)
                .onStart { binding.livePreviewTxtGood.visibility = View.VISIBLE }
                .onEnd { binding.livePreviewTxtGood.visibility = View.INVISIBLE }
                .duration(1000)
                .repeat(1)
                .playOn(binding.livePreviewTxtGood)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setStatusBarColor(getColor(R.color.black))

        binding.livePreviewImgBack.setOnClickListener {
            finish()
        }
        /*    val facingSwitch = findViewById<ToggleButton>(R.id.facing_switch)
            facingSwitch.setOnCheckedChangeListener(this)*/
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
                            repsChangedListener
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
        createCameraSource(selectedModel)
        startCameraSource()
    }

    /** Stops the camera. */
    override fun onPause() {
        super.onPause()
        binding.livePreviewPreviewView.stop()
    }

    public override fun onDestroy() {
        super.onDestroy()
        if (cameraSource != null) {
            cameraSource?.release()
        }
    }

    companion object {
        private const val POSE_DETECTION = "Pose Detection"

        private const val TAG = "LivePreviewActivity"
    }
}
