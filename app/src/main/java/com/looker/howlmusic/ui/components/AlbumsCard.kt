package com.looker.howlmusic.ui.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.TweenSpec
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.looker.howlmusic.model.Album
import com.looker.howlmusic.utils.Constants.fadeInDuration

@Composable
fun AlbumsCard(
    album: Album,
    modifier: Modifier = Modifier,
    columnCount: Int,
    onAlbumClick: (Long, String, String) -> Unit,
) {
    val cardWidth =
        (LocalContext.current.resources.displayMetrics.widthPixels / columnCount).dp / LocalDensity.current.density - 20.dp

    AlbumsCard(
        album = album,
        cardWidth = cardWidth,
        modifier = modifier,
        onAlbumClick = onAlbumClick
    )
}

@Composable
private fun AlbumsCard(
    modifier: Modifier = Modifier,
    album: Album,
    cardWidth: Dp,
    onAlbumClick: (Long, String, String) -> Unit,
) {

    var cardColor by remember {
        mutableStateOf(Color.Transparent)
    }

    val animatedColor by animateColorAsState(
        targetValue = cardColor,
        animationSpec = TweenSpec(
            durationMillis = fadeInDuration
        )
    )

    Card(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .clickable { onAlbumClick(album.albumId, album.albumName, album.artistName) },
        backgroundColor = animatedColor
    ) {
        AlbumsItem(album = album, getColor = { cardColor = it.copy(0.4f) }, imageSize = cardWidth)
    }
}

@Composable
fun AlbumsItem(
    album: Album,
    imageSize: Dp,
    getColor: (Color) -> Unit,
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        HowlImage(
            data = album.albumArtUri,
            modifier = Modifier.size(imageSize)
        ) { vibrantColor ->
            getColor(vibrantColor)
        }
        AlbumsItemText(album = album)
        Spacer(modifier = Modifier.height(8.dp))
    }
}

@Composable
fun AlbumsItemText(
    album: Album,
) {
    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        HeaderText(text = album.albumName)
        BodyText(text = album.artistName)
    }
}