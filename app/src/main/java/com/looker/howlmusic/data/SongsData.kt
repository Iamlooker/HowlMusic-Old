package com.looker.howlmusic.data

import android.content.ContentUris
import android.content.Context
import android.database.Cursor
import com.looker.howlmusic.model.Song
import com.looker.howlmusic.utils.Constants.artworkUri
import com.looker.howlmusic.utils.Constants.externalUri
import com.looker.howlmusic.utils.Constants.isMusic
import com.looker.howlmusic.utils.Constants.songProjection
import com.looker.howlmusic.utils.Constants.sortOrderSong

class SongsData(private val context: Context) {

    private fun createSongCursor(): Cursor? {
        return try {
            context.applicationContext.contentResolver.query(
                externalUri,
                songProjection,
                isMusic,
                null,
                sortOrderSong
            )
        } catch (e: NullPointerException) {
            null
        }
    }

    fun getSongList(): MutableList<Song> {
        val list: MutableList<Song> = mutableListOf()
        val songCursor = createSongCursor()
        if (songCursor != null && songCursor.moveToFirst()) {
            do {
                val songName = songCursor.getString(0)
                val artistName = songCursor.getString(1)
                val songId = songCursor.getLong(2)
                val songDurationRaw = songCursor.getInt(3)
                val songDuration =
                    (songDurationRaw / 60).toString() + (songDurationRaw % 60).toString()
                val albumArtUri = ContentUris.withAppendedId(artworkUri, songId)
                list.add(Song(songName, artistName, songDuration, albumArtUri))
            } while (songCursor.moveToNext())
        }
        return list
    }

    fun getPerAlbumList(): MutableList<Song> {
        val list: MutableList<Song> = mutableListOf()
        val songCursor = createSongCursor()
        if (songCursor != null && songCursor.moveToFirst()) {
            do {
                val songName = songCursor.getString(0)
                val artistName = songCursor.getString(1)
                val songId = songCursor.getLong(2)
                val songDurationRaw = songCursor.getInt(3)
                val songDuration =
                    (songDurationRaw / 60).toString() + (songDurationRaw % 60).toString()
                val albumArtUri = ContentUris.withAppendedId(artworkUri, songId)
                list.add(Song(songName, artistName, songDuration, albumArtUri))
            } while (songCursor.moveToNext())
        }
        return list
    }

}