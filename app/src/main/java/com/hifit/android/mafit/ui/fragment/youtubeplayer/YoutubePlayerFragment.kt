package com.hifit.android.mafit.ui.fragment.youtubeplayer


import android.content.res.Configuration
import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.hifit.android.mafit.base.BaseFragment
import com.hifit.android.mafit.databinding.FragmentYoutubePlayerBinding
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener


class YoutubePlayerFragment :
    BaseFragment<FragmentYoutubePlayerBinding>(com.hifit.android.mafit.R.layout.fragment_youtube_player) {
    private val args: YoutubePlayerFragmentArgs by navArgs()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.youtubeplayerImgBack.setOnClickListener {
            findNavController().popBackStack()
        }

        val youtubeId = args.youtubeId
        viewLifecycleOwner.lifecycle.addObserver(binding.youtubeplayer)

        binding.youtubeplayer.addYouTubePlayerListener(object : AbstractYouTubePlayerListener() {
            override fun onReady(youTubePlayer: YouTubePlayer) {
                youTubePlayer.loadVideo(youtubeId, 0f)
            }
        })
    }
}