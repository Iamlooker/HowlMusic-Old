package com.looker.howlmusic.ui.home

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.google.accompanist.insets.LocalWindowInsets
import com.google.accompanist.insets.rememberInsetsPaddingValues
import com.looker.howlmusic.MainDestinations
import com.looker.howlmusic.model.Album
import com.looker.howlmusic.ui.components.AlbumsCard
import com.looker.howlmusic.viewmodels.AlbumsViewModel

@Composable
fun Albums(
    navController: NavController,
    viewModel: AlbumsViewModel = viewModel(),
) {
    Albums(
        navController = navController,
        albumsList = viewModel.albumsList,
        columnCount = viewModel.albumsColumn
    )
}

@Composable
private fun Albums(
    navController: NavController,
    albumsList: MutableList<Album>,
    columnCount: Int,
) {
    AlbumsList(navController = navController, albumsList = albumsList, columnCount = columnCount)
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun AlbumsList(
    navController: NavController,
    albumsList: MutableList<Album>,
    columnCount: Int,
) {
    LazyVerticalGrid(
        cells = GridCells.Fixed(count = columnCount),
        contentPadding = rememberInsetsPaddingValues(
            insets = LocalWindowInsets.current.systemBars,
            applyTop = true
        )
    ) {
        items(albumsList) { album ->
            AlbumsCard(
                album = album,
                columnCount = columnCount,
                modifier = Modifier.padding(10.dp)
            ) {
                navController.navigate("${MainDestinations.ALBUMS_DETAILS_ROUTE}/${album.albumName}/${album.artistName}/${album.albumId}")
            }
        }
    }
}