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
import com.google.accompanist.insets.LocalWindowInsets
import com.google.accompanist.insets.rememberInsetsPaddingValues
import com.looker.howlmusic.model.Album
import com.looker.howlmusic.ui.components.AlbumsCard
import com.looker.howlmusic.viewmodels.AlbumsViewModel

@Composable
fun Albums(
    viewModel: AlbumsViewModel = viewModel(),
    onAlbumClick: (Long, String?, String?) -> Unit,
) {
    Albums(
        albumsList = viewModel.albums.value,
        columnCount = viewModel.albumsColumn,
        onAlbumClick = onAlbumClick
    )
}

@Composable
private fun Albums(
    albumsList: MutableList<Album>,
    columnCount: Int,
    onAlbumClick: (Long, String?, String?) -> Unit,
) {
    AlbumsList(albumsList = albumsList, columnCount = columnCount, onAlbumClick = onAlbumClick)
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun AlbumsList(
    albumsList: MutableList<Album>,
    columnCount: Int,
    onAlbumClick: (Long, String?, String?) -> Unit,
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
                modifier = Modifier.padding(10.dp),
                onAlbumClick = onAlbumClick,
                showImage = true
            )
        }
    }
}