package com.looker.howlmusic.data

import android.content.Context
import android.database.Cursor
import com.looker.howlmusic.model.Album
import com.looker.howlmusic.utils.Constants.albumProjection
import com.looker.howlmusic.utils.Constants.externalUri
import com.looker.howlmusic.utils.Constants.isMusic
import com.looker.howlmusic.utils.Constants.sortOrderAlbum

class AlbumsData(private val context: Context) {

    private fun createAlbumCursor(): Cursor? {
        return try {
            context.contentResolver.query(
                externalUri,
                albumProjection,
                isMusic,
                null,
                sortOrderAlbum
            )
        } catch (e: NullPointerException) {
            null
        }
    }

    fun getAlbumList(): MutableList<Album> {
        val list: MutableList<Album> = mutableListOf()
        val albumCursor = createAlbumCursor()
        if (albumCursor != null && albumCursor.moveToFirst()) {
            do {
                val albumId = albumCursor.getLong(0)
                val albumName = albumCursor.getString(1)
                val artistName = albumCursor.getString(2)
                list.add(Album(albumId, albumName, artistName))

            } while (albumCursor.moveToNext())
        }
        return list.distinct().toMutableList()
    }
}