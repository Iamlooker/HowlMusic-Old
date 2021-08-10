package com.looker.howlmusic.model

import android.net.Uri

data class Song(
    val songUri: Uri,
    val albumId: Long,
    val songName: String,
    val artistName: String,
    val songDurationMillis: Int,
)