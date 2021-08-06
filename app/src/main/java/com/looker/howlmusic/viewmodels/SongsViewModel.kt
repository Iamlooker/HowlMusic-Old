package com.looker.howlmusic.viewmodels

import android.app.Application
import android.media.AudioAttributes
import android.media.MediaPlayer
import android.net.Uri
import androidx.lifecycle.AndroidViewModel
import com.looker.howlmusic.data.SongsData
import com.looker.howlmusic.model.Song

class SongsViewModel(application: Application) : AndroidViewModel(application) {
    val songsList: MutableList<Song> = SongsData(application).getSongList()

    private val app = application
    fun play(myUri: Uri) {
        MediaPlayer().apply {
            setAudioAttributes(
                AudioAttributes.Builder()
                    .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                    .setUsage(AudioAttributes.USAGE_MEDIA)
                    .build()
            )
            setDataSource(app, myUri)
            prepare()
            start()

        }
    }
}