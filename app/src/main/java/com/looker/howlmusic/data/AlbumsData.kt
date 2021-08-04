package com.looker.howlmusic.data

import android.content.ContentUris
import android.content.Context
import android.database.Cursor
import com.looker.howlmusic.model.Album
import com.looker.howlmusic.utils.Constants.albumProjection
import com.looker.howlmusic.utils.Constants.artworkUri
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
                val albumName = albumCursor.getString(0)
                val artistName = albumCursor.getString(1)
                val albumId = albumCursor.getLong(2)
                val albumArtUri = ContentUris.withAppendedId(artworkUri, albumId)
                list.add(Album(albumName, artistName, albumArtUri, albumId))

                for (i in list.indices) {
                    if (i > 0 && list[i] == list[i - 1]) {
                        list.remove(list[i])
                    }
                }

            } while (albumCursor.moveToNext())
        }
        return list
    }
}