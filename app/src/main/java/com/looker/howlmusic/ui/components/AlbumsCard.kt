package com.looker.howlmusic.ui.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.TweenSpec
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.looker.howlmusic.model.Album
import com.looker.howlmusic.ui.theme.Typography
import com.looker.howlmusic.utils.Constants.artworkUri
import com.looker.howlmusic.utils.Constants.fadeInDuration
import com.looker.howlmusic.utils.rememberDominantColorState
import kotlinx.coroutines.launch

@Composable
fun AlbumsCard(
    album: Album,
    modifier: Modifier = Modifier,
    columnCount: Int,
    showImage: Boolean,
    onAlbumClick: (Album) -> Unit,
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
    onAlbumClick: (Album) -> Unit,
) {
    Card(modifier = modifier) {
        AlbumsItem(album = album, imageSize = cardWidth, onAlbumClick = onAlbumClick)
    }
}

@Composable
fun AlbumsItem(
    album: Album,
    imageSize: Dp,
    onAlbumClick: (Album) -> Unit,
) {

    val backgroundColor = rememberDominantColorState()

    val albumArtUri = album.albumId.artworkUri

    LaunchedEffect(albumArtUri) {
        launch {
            backgroundColor.updateColorsFromImageUrl(albumArtUri.toString())
        }
    }

    val animatedColor by animateColorAsState(
        targetValue = backgroundColor.color.copy(0.3f),
        animationSpec = TweenSpec(
            durationMillis = fadeInDuration
        )
    )

    Column(
        modifier = Modifier
            .wrapContentSize()
            .background(animatedColor)
            .clickable(onClick = {
                onAlbumClick(album)
            }),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        HowlImage(
            data = album.albumId.artworkUri,
            modifier = Modifier.size(imageSize)
        )
        AlbumsItemText(album = album)
        Spacer(
            modifier = Modifier
                .height(8.dp)
                .fillMaxWidth()
        )
    }
}

@Composable
fun AlbumsItemText(album: Album) {
    WrappedText(text = album.albumName)
    WrappedText(text = album.artistName, style = Typography.body2)
}