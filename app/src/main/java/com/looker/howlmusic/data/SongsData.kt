package com.looker.howlmusic.data

import android.content.Context
import android.database.Cursor
import android.net.Uri
import com.looker.howlmusic.model.Song
import com.looker.howlmusic.utils.Constants.externalUri
import com.looker.howlmusic.utils.Constants.isMusic
import com.looker.howlmusic.utils.Constants.songProjection
import com.looker.howlmusic.utils.Constants.sortOrderSong
import java.io.File

class SongsData(private val context: Context) {

    private fun createSongCursor(): Cursor? {
        return try {
            context.contentResolver.query(
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
                val songId = songCursor.getLong(0)
                val albumId = songCursor.getLong(1)
                val songName = songCursor.getString(2)
                val artistName = songCursor.getString(3)
                val songDurationMillis = songCursor.getInt(4)
                val songUri = Uri.parse(externalUri.toString() + File.separator + songId)
                list.add(Song(songUri, albumId, songName, artistName, songDurationMillis))
            } while (songCursor.moveToNext())
        }
        return list
    }
}