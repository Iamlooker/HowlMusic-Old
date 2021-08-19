package com.looker.howlmusic.ui.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.TweenSpec
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.looker.howlmusic.model.Album
import com.looker.howlmusic.ui.theme.Typography
import com.looker.howlmusic.utils.Constants.fadeInDuration
import com.looker.howlmusic.utils.Constants.itemSize
import com.looker.howlmusic.utils.Extension.artworkUri
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
        if (showImage) itemSize(LocalContext.current, false, columnCount, 20.dp)
        else 0.dp
    )

    AlbumsCard(
        album = album,
        cardWidth = cardWidth,
        modifier = modifier.padding(10.dp),
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
    modifier: Modifier = Modifier,
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
        modifier = modifier
            .wrapContentSize()
            .background(animatedColor)
            .clickable { onAlbumClick(album) },
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        HowlImage(
            data = albumArtUri,
            modifier = Modifier.size(imageSize)
        )
        AlbumsItemText(
            modifier = Modifier.padding(5.dp),
            album = album
        )
    }
}

@Composable
fun AlbumsItemText(modifier: Modifier = Modifier, album: Album) {
    WrappedText(
        modifier = modifier,
        text = album.albumName,
        textAlign = TextAlign.Center
    )
    WrappedText(
        modifier = modifier,
        text = album.artistName,
        style = Typography.body2,
        textAlign = TextAlign.Center
    )
}