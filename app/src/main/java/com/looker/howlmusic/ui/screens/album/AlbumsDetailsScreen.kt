package com.looker.howlmusic.ui.screens.album

import android.content.ContentUris
import androidx.compose.animation.*
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.looker.howlmusic.ui.composables.AlbumsArt
import com.looker.howlmusic.ui.composables.BodyText
import com.looker.howlmusic.ui.composables.HeaderText
import com.looker.howlmusic.ui.theme.Typography
import com.looker.howlmusic.utils.Constants.artworkUri

@Composable
fun DetailsMain(
    albumName: String?,
    artistName: String?,
    albumId: Long,
) {
    AlbumDetailHeader(albumName = albumName, artistName = artistName, albumId = albumId)
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun AlbumDetailHeader(
    albumName: String?,
    artistName: String?,
    albumId: Long,
) {

    val albumArt = ContentUris.withAppendedId(artworkUri, albumId)
    val composeAnimation = MutableTransitionState(initialState = false).apply {
        targetState = true
    }
    var color by remember {
        mutableStateOf(
            Color.Transparent
        )
    }

    AnimatedVisibility(
        visibleState = composeAnimation,
        enter = fadeIn(initialAlpha = 0f, animationSpec = tween(durationMillis = 500)) + expandIn()
    ) {
        Box(modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    listOf(
                        color,
                        Color.Transparent
                    )
                )
            )
        )
        Column(
            Modifier.fillMaxWidth(),
            horizontalAlignment = CenterHorizontally
        ) {

            AlbumsArt(
                data = albumArt,
                modifier = Modifier.padding(20.dp)
            ) {
                color = it
            }
            HeaderText(
                text = albumName,
                style = Typography.h5
            )
            BodyText(
                text = artistName
            )
        }
    }
}