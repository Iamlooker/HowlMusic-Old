package com.looker.howlmusic.ui.screens.album

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.looker.howlmusic.model.Album
import com.looker.howlmusic.ui.composables.AlbumsArt
import com.looker.howlmusic.ui.composables.BodyText
import com.looker.howlmusic.ui.composables.HeaderText

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun AlbumsList(
    albumsList: List<Album> = emptyList(),
    navController: NavController,
) {

    LazyVerticalGrid(
        cells = GridCells.Fixed(count = 2),
        contentPadding = PaddingValues(bottom = 60.dp)
    ) {
        items(albumsList) { album ->
            AlbumsItem(
                album = album,
                modifier = Modifier.padding(10.dp)
            ) {
                navController.navigate("albums_details/${album.albumName}/${album.artistName}/${album.albumId}")
            }
        }
    }
}

@Composable
fun AlbumsItem(
    album: Album,
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
) {

    var cardColor by remember {
        mutableStateOf(Color.Transparent)
    }
    val albumArtWidth =
        (LocalContext.current.resources.displayMetrics.widthPixels / 2).dp / LocalDensity.current.density - 20.dp

    Card(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        backgroundColor = cardColor
    ) {
        Column(
            modifier = Modifier.clickable { onClick() },
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AlbumsArt(
                data = album.albumArtUri,
                modifier = Modifier.size(albumArtWidth)
            ) {
                cardColor = it
            }
            HeaderText(
                text = album.albumName
            )
            BodyText(
                text = album.artistName
            )
        }
    }
}