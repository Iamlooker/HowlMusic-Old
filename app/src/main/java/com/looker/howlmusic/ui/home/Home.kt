package com.looker.howlmusic.ui.home

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Album
import androidx.compose.material.icons.rounded.MusicNote
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.looker.howlmusic.R

fun NavGraphBuilder.addHomeGraph(
    onAlbumClicked: (Long, String?, String?, NavBackStackEntry) -> Unit,
) {

    composable(HomeSections.ALBUMS.route) { from ->
        Albums(onAlbumClick = { albumId, albumName, artistName ->
            onAlbumClicked(albumId, albumName, artistName, from)
        })
    }
    composable(HomeSections.SONGS.route) {
        Songs()
    }

}

enum class HomeSections(
    @StringRes val title: Int,
    val icon: ImageVector,
    val route: String,
) {
    ALBUMS(R.string.albums, Icons.Rounded.Album, "home/albums"),
    SONGS(R.string.songs, Icons.Rounded.MusicNote, "home/songs")
}