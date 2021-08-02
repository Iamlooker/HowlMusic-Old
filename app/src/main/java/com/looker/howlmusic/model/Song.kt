package com.looker.howlmusic.model

import android.net.Uri

data class Song(
    val songName: String,
    val artistName: String,
    val songDuration: String,
    val albumArtUri: Uri? = null

)