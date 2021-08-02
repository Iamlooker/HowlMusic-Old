package com.looker.howlmusic.model

import android.graphics.Bitmap
import android.net.Uri

data class Album(
    val albumName: String,
    val artistName: String,
    val albumArtUri: Uri? = null,
    val albumId: Long
)