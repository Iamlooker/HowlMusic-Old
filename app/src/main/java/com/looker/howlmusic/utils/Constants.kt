package com.looker.howlmusic.utils

import android.net.Uri
import android.provider.MediaStore

object Constants {

    const val NOTIFICATION_CHANNEL_ID = "music"
    const val NOTIFICATION_CHANNEL_NAME = "Music"
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
        MediaStore.Audio.Media.TITLE,
        MediaStore.Audio.Media.ARTIST,
        MediaStore.Audio.Media.ALBUM_ID,
        MediaStore.Audio.Media.DURATION,
        MediaStore.Audio.Media._ID
    )

    val artworkUri: Uri = Uri.parse("content://media/external/audio/albumart")

    val externalUri: Uri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI

    const val isMusic: String = MediaStore.Audio.Media.IS_MUSIC + " != 0"

    const val sortOrderAlbum: String = MediaStore.Audio.Media.ALBUM + " COLLATE NOCASE ASC"
    const val sortOrderSong: String = MediaStore.Audio.Media.TITLE + " COLLATE NOCASE ASC"

    const val fadeInDuration = 500
}