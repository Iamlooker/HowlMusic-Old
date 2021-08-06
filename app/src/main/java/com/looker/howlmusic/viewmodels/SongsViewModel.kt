package com.looker.howlmusic.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.SimpleExoPlayer
import com.looker.howlmusic.data.SongsData
import com.looker.howlmusic.model.Song

class SongsViewModel(application: Application) : AndroidViewModel(application) {
    val songsList: MutableList<Song> = SongsData(application).getSongList()

    private val app = application

    private fun exoPlayer() = SimpleExoPlayer.Builder(app).build()

    fun playSong(song: Song) {
        val player = exoPlayer()
        val item = MediaItem.fromUri(song.songUri)
        player.setMediaItem(item)
        player.prepare()
        player.play()
    }
}