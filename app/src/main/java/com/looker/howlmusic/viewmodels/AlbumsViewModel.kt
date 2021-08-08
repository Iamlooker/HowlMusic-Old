package com.looker.howlmusic.viewmodels

import android.app.Application
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.looker.howlmusic.data.AlbumsData
import com.looker.howlmusic.data.SongsData
import com.looker.howlmusic.model.Album
import com.looker.howlmusic.model.Song
import kotlinx.coroutines.launch

class AlbumsViewModel(application: Application) : AndroidViewModel(application) {

    val albumsColumn = 2
    val albums: MutableState<MutableList<Album>> = mutableStateOf(mutableListOf())

    init {
        viewModelScope.launch {
            val albumsList = AlbumsData(application).getAlbumList()
            albums.value = albumsList
        }
    }

    private val albumSongList: MutableList<Song> = SongsData(application).getSongList()

    fun getSongList(albumId: Long): MutableList<Song> {
        val list: MutableList<Song> = mutableListOf()
        albumSongList.forEach { song -> if (song.albumId == albumId) list.add(song) }
        return list
    }
}