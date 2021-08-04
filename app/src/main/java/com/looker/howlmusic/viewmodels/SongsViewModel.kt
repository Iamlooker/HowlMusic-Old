package com.looker.howlmusic.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.looker.howlmusic.data.SongsData
import com.looker.howlmusic.model.Song

class SongsViewModel(application: Application) : AndroidViewModel(application) {
    val songsList: MutableList<Song> = SongsData(application).getSongList()
}