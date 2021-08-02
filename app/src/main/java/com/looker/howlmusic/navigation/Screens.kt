package com.looker.howlmusic.navigation

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Album
import androidx.compose.material.icons.filled.MusicNote
import androidx.compose.ui.graphics.vector.ImageVector
import com.looker.howlmusic.R

sealed class Screens(val route: String, @StringRes val resourceId: Int, val icon: ImageVector) {
    object Albums : Screens("albums", R.string.albums, Icons.Default.Album)
    object Songs : Screens("songs", R.string.songs, Icons.Default.MusicNote)
    object AlbumsDetail :
        Screens("albums_details/{albumName}/{artistName}/{albumId}", 0, Icons.Default.Album)

    object Items {
        val items = listOf(
            Songs,
            Albums
        )
    }
}

sealed class AlbumsScreens(
    val route: String,
) {
    object AlbumsMain : AlbumsScreens("albums_main")
    object AlbumsDetail : AlbumsScreens("albums_details/{albumName}/{artistName}/{albumId}")
}
