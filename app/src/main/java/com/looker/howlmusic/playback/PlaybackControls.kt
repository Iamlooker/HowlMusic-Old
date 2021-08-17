package com.looker.howlmusic.playback

import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.SimpleExoPlayer


object PlaybackControls {

    private val playbackService = PlaybackService()

    fun playSongs(player: SimpleExoPlayer, mediaItems: ArrayList<MediaItem>) {
        playbackService.initPlayer(player, mediaItems)
    }
}