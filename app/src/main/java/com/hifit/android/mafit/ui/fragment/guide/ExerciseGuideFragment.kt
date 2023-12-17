package com.hifit.android.mafit.ui.fragment.guide

import android.content.Intent
import android.os.Bundle
import android.view.View
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.Player
import com.google.android.exoplayer2.util.Util
import com.hifit.android.mafit.R
import com.hifit.android.mafit.base.BaseFragment
import com.hifit.android.mafit.databinding.FragmentExerciseGuideBinding
import com.hifit.android.mafit.mlkit.LivePreviewActivity


class ExerciseGuideFragment :
    BaseFragment<FragmentExerciseGuideBinding>(R.layout.fragment_exercise_guide) {
    private var player: ExoPlayer? = null
    private var playWhenReady = true
    private var currentWindow = 0
    private var playbackPosition = 0L
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.exerciseGuideBtnSkip.setOnClickListener {
            val intent = Intent(requireContext(), LivePreviewActivity::class.java)
            startActivity(intent)
        }
    }


    override fun onStart() {
        super.onStart()
        if (Util.SDK_INT >= 24) {
            initializePlayer()
        }
    }

    override fun onResume() {
        super.onResume()
        if ((Util.SDK_INT < 24 || player == null)) {
            initializePlayer()
        }
    }

    override fun onPause() {
        super.onPause()
        if (Util.SDK_INT < 24) {
            releasePlayer()
        }
    }

    override fun onStop() {
        super.onStop()
        if (Util.SDK_INT >= 24) {
            releasePlayer()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        player?.release()
    }

    private fun initializePlayer() {
        player = ExoPlayer.Builder(requireActivity()).build().also { exoPlayer ->
            binding.exerciseGuidePlayerView.player = exoPlayer
            val mediaItem =
                MediaItem.fromUri("http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4")
            exoPlayer.setMediaItem(mediaItem)

            exoPlayer.playWhenReady = playWhenReady
            exoPlayer.seekTo(currentWindow, playbackPosition)
            exoPlayer.prepare()
        }

        player?.addListener(object : Player.Listener {

            override fun onPlaybackStateChanged(playbackState: Int) {
                super.onPlaybackStateChanged(playbackState)
                if (playbackState == Player.STATE_ENDED) {
                    val intent = Intent(requireContext(), LivePreviewActivity::class.java)
                    startActivity(intent)
                }
            }
        })
    }

    private fun releasePlayer() {
        player?.run {
            playbackPosition = this.currentPosition
            currentWindow = this.currentMediaItemIndex
            playWhenReady = this.playWhenReady
            release()
        }
        player = null
    }

}