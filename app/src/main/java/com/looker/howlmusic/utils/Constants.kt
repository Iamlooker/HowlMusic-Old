package com.looker.howlmusic.utils

import android.net.Uri
import android.os.Build
import android.provider.MediaStore

object Constants {

    val permission = arrayOf(
        android.Manifest.permission.READ_EXTERNAL_STORAGE
    )

    val albumProjection = arrayOf(
        MediaStore.Audio.Media.ALBUM,
        MediaStore.Audio.Media.ARTIST,
        MediaStore.Audio.Media.ALBUM_ID
    )

    val songProjection = arrayOf(
        MediaStore.Audio.Media.TITLE,
        MediaStore.Audio.Media.ARTIST,
        MediaStore.Audio.Media.ALBUM_ID,
        MediaStore.Audio.Media.DURATION
    )

    val artworkUri: Uri = Uri.parse("content://media/external/audio/albumart")

    val externalUri: Uri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI

    const val isMusic: String = MediaStore.Audio.Media.IS_MUSIC + " != 0"

    const val sortOrderAlbum: String = MediaStore.Audio.Media.ALBUM + " COLLATE NOCASE ASC"
    const val sortOrderSong: String = MediaStore.Audio.Media.TITLE + " COLLATE NOCASE ASC"
}