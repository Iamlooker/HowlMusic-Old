package com.looker.howlmusic.ui.home

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import com.google.accompanist.insets.LocalWindowInsets
import com.google.accompanist.insets.rememberInsetsPaddingValues
import com.looker.howlmusic.model.Album
import com.looker.howlmusic.ui.components.AlbumsCard
import com.looker.howlmusic.utils.Constants.columnCount
import com.looker.howlmusic.viewmodels.AlbumsViewModel

@Composable
fun Albums(
    viewModel: AlbumsViewModel = viewModel(),
    onAlbumClick: (Album) -> Unit,
) {
    Albums(
        albumsList = viewModel.albums.value,
        columnCount = columnCount,
        onAlbumClick = onAlbumClick
    )
}

@Composable
private fun Albums(
    albumsList: MutableList<Album>,
    columnCount: Int,
    onAlbumClick: (Album) -> Unit,
) {
    AlbumsList(albumsList = albumsList, columnCount = columnCount, onAlbumClick = onAlbumClick)
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun AlbumsList(
    albumsList: MutableList<Album>,
    columnCount: Int,
    onAlbumClick: (Album) -> Unit,
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
                onAlbumClick = onAlbumClick,
                showImage = true
            )
        }
    }
}