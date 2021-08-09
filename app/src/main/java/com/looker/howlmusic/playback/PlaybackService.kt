package com.looker.howlmusic.playback

import android.app.Service
import android.content.Intent
import android.os.IBinder
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.SimpleExoPlayer
import com.looker.howlmusic.model.Song

class PlaybackService : Service() {

    var player: SimpleExoPlayer? = null
    var songsList: MutableList<Song> = mutableListOf()

    override fun onCreate() {
        super.onCreate()

        val mediaItem: ArrayList<MediaItem> = arrayListOf()

        songsList.forEach {
            mediaItem.add(MediaItem.fromUri(it.songUri))
        }

        player?.setMediaItems(mediaItem)
        player?.prepare()
    }


    override fun onDestroy() {
        super.onDestroy()
        player?.release()
        player = null
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int = START_STICKY

    override fun onBind(intent: Intent): IBinder? = null
}