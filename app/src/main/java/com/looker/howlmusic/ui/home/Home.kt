package com.looker.howlmusic.ui.home

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Album
import androidx.compose.material.icons.rounded.MusicNote
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.looker.howlmusic.model.Album

fun NavGraphBuilder.addHomeGraph(
    onAlbumClicked: (Album, NavBackStackEntry) -> Unit,
) {
    composable(HomeSections.SONGS.route) { Songs() }
    composable(HomeSections.ALBUMS.route) { from ->
        Albums(
            onAlbumClick = { album ->
                onAlbumClicked(album, from)
            }
        )
    }
}

enum class HomeSections(
    val title: String,
    val icon: ImageVector,
    val route: String,
) {
    SONGS("Songs", Icons.Rounded.MusicNote, "home/songs"),
    ALBUMS("Albums", Icons.Rounded.Album, "home/albums")
}