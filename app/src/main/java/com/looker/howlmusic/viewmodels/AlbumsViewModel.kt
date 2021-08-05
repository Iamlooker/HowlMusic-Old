package com.looker.howlmusic.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.looker.howlmusic.data.AlbumsData
import com.looker.howlmusic.data.SongsData
import com.looker.howlmusic.model.Album
import com.looker.howlmusic.model.Song

class AlbumsViewModel(application: Application) : AndroidViewModel(application) {

    val albumsColumn = 2
    val albumsList: MutableList<Album> = AlbumsData(application).getAlbumList()

    private val albumSongList: MutableList<Song> = SongsData(application).getSongList()

    fun getSongList(albumId: Long): MutableList<Song> {
        val list: MutableList<Song> = mutableListOf()
        albumSongList.forEach { song -> if (song.albumId == albumId) list.add(song) }
        return list
    }
}