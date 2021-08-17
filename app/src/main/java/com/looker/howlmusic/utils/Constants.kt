package com.looker.howlmusic.utils

import android.content.Context
import android.net.Uri
import android.provider.MediaStore
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

object Constants {

    const val NOTIFICATION_CHANNEL_ID = "music_player"
    const val NOTIFICATION_CHANNEL_NAME = "Music Player"
    const val NOTIFICATION_ID = 1

    val permission = arrayOf(
        android.Manifest.permission.READ_EXTERNAL_STORAGE
    )

    val albumProjection = arrayOf(
        MediaStore.Audio.Media.ALBUM_ID,
        MediaStore.Audio.Media.ALBUM,
        MediaStore.Audio.Media.ARTIST
    )

    val songProjection = arrayOf(
        MediaStore.Audio.Media._ID,
        MediaStore.Audio.Media.ALBUM_ID,
        MediaStore.Audio.Media.TITLE,
        MediaStore.Audio.Media.ARTIST,
        MediaStore.Audio.Media.DURATION
    )

    val externalUri: Uri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI
    const val isMusic: String = MediaStore.Audio.Media.IS_MUSIC + " != 0"
    const val sortOrderAlbum: String = MediaStore.Audio.Media.ALBUM + " COLLATE NOCASE ASC"
    const val sortOrderSong: String = MediaStore.Audio.Media.TITLE + " COLLATE NOCASE ASC"

    val Long.artworkUri: Uri
        get() = Uri.parse("content://media/external/audio/albumart/$this")

    val Long.pathUri: Uri
        get() = Uri.parse("$externalUri/$this")

    const val fadeInDuration = 500

    fun itemSize(context: Context, height: Boolean, count: Int, padding: Dp = 0.dp): Dp {
        val screenDensity = context.resources.displayMetrics.density
        val screenHeight = context.resources.displayMetrics.heightPixels
        val screenWidth = context.resources.displayMetrics.widthPixels
        val itemSizeInPx = if (height) {
            screenHeight / count
        } else {
            screenWidth / count
        }
        val itemSize = itemSizeInPx.dp / screenDensity

        return itemSize - padding
    }
}