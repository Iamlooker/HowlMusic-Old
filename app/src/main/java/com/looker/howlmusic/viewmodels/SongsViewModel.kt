package com.looker.howlmusic.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.SimpleExoPlayer
import com.looker.howlmusic.data.SongsData
import com.looker.howlmusic.model.Song
import com.looker.howlmusic.playback.PlaybackControls.playSongs
import kotlinx.coroutines.flow.MutableStateFlow

class SongsViewModel(application: Application) : AndroidViewModel(application) {
    val songsList = SongsData(application).getSongList()
    val player = SimpleExoPlayer.Builder(application).build()
    var playingSong = MutableStateFlow(songsList[0])
    private val mediaItems: ArrayList<MediaItem> = arrayListOf()


    fun addMediaItems(song: Song) {
        val pos = songsList.indexOf(song)
        for (songs in pos until songsList.size) {
            mediaItems.add(MediaItem.fromUri(songsList[songs].songUri))
        }
    }

    fun setCurrentSong(song: Song) {
        playingSong.value = song
    }

    fun playSongs() {
        playSongs(player, mediaItems)
    }
}