package com.looker.howlmusic.data

import android.content.ContentUris
import android.content.Context
import android.database.Cursor
import android.net.Uri
import com.looker.howlmusic.model.Song
import com.looker.howlmusic.utils.Constants.artworkUri
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
                val songName = songCursor.getString(0)
                val artistName = songCursor.getString(1)
                val albumId = songCursor.getLong(2)
                val songDurationRaw = songCursor.getInt(3)
                val songId = songCursor.getLong(4)
                val songDuration = convertDuration(songDurationRaw)
                val albumArtUri = ContentUris.withAppendedId(artworkUri, albumId)
                val songUri = Uri.parse(externalUri.toString() + File.separator + songId)
                list.add(Song(songName, artistName, songDuration, albumArtUri, albumId, songUri))
            } while (songCursor.moveToNext())
        }
        return list
    }

    private fun convertDuration(dur: Int): String {
        val mns: Int = dur / 60000 % 60000
        val scs: Int = dur % 60000 / 1000

        return "$mns:$scs"
    }
}