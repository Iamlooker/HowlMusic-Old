package com.looker.howlmusic.ui.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.TweenSpec
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.background
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
import com.looker.howlmusic.utils.Constants.artworkUri
import com.looker.howlmusic.utils.Constants.fadeInDuration

@Composable
fun AlbumsCard(
    album: Album,
    modifier: Modifier = Modifier,
    columnCount: Int,
    showImage: Boolean,
    onAlbumClick: (Long, String?, String?) -> Unit,
) {
    val cardWidth by
    animateDpAsState(
        if (showImage) {
            (LocalContext.current.resources.displayMetrics.widthPixels / columnCount
                    ).dp / LocalDensity.current.density - 20.dp
        } else 0.dp
    )


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
    onAlbumClick: (Long, String?, String?) -> Unit,
) {
    Card(modifier = modifier) {
        AlbumsItem(album = album, imageSize = cardWidth, onAlbumClick = onAlbumClick)
    }
}

@Composable
fun AlbumsItem(
    album: Album,
    imageSize: Dp,
    onAlbumClick: (Long, String?, String?) -> Unit,
) {

    var backgroundColor by remember {
        mutableStateOf(Color.Transparent)
    }

    val animatedColor by animateColorAsState(
        targetValue = backgroundColor,
        animationSpec = TweenSpec(
            durationMillis = fadeInDuration
        )
    )

    Column(
        modifier = Modifier
            .wrapContentSize()
            .background(animatedColor)
            .clickable(onClick = {
                onAlbumClick(album.albumId,
                    album.albumName,
                    album.artistName)
            }),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        HowlImage(
            data = album.albumId.artworkUri,
            modifier = Modifier.size(imageSize)
        ) { vibrantColor ->
            backgroundColor = vibrantColor.copy(0.4f)
        }
        AlbumsItemText(album = album)
        Spacer(
            modifier = Modifier
                .height(8.dp)
                .fillMaxWidth()
        )
    }
}

@Composable
fun AlbumsItemText(
    album: Album,
) {
    HeaderText(text = album.albumName)
    BodyText(text = album.artistName)
}