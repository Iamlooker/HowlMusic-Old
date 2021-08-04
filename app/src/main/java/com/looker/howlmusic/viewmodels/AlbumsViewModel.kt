package com.looker.howlmusic.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.looker.howlmusic.data.AlbumsData
import com.looker.howlmusic.model.Album

class AlbumsViewModel(application: Application) : AndroidViewModel(application) {

    val albumsColumn = 2
    val albumsList: MutableList<Album> = AlbumsData(application).getAlbumList()
}