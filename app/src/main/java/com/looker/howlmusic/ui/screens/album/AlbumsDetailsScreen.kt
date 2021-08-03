package com.looker.howlmusic.ui.screens.album

import android.content.ContentUris
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandIn
import androidx.compose.animation.fadeIn
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.google.accompanist.insets.statusBarsHeight
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
    var backgroundGradient by remember {
        mutableStateOf(Color.Transparent)
    }
    Column {
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .statusBarsHeight()
                .background(backgroundGradient)
        )
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .background(
                    Brush.verticalGradient(
                        listOf(
                            backgroundGradient,
                            Color.Transparent
                        )
                    )
                )
        ) {
            AlbumDetailHeader(
                albumName = albumName,
                artistName = artistName,
                albumId = albumId
            ) { color ->
                backgroundGradient = color.copy(0.4f)
            }
        }
    }

}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun AlbumDetailHeader(
    modifier: Modifier = Modifier,
    albumName: String?,
    artistName: String?,
    albumId: Long,
    content: (Color) -> Unit,
) {

    val albumArt = ContentUris.withAppendedId(artworkUri, albumId)
    val composeAnimation = MutableTransitionState(initialState = false).apply {
        targetState = true
    }

    AnimatedVisibility(
        visibleState = composeAnimation,
        enter = fadeIn(initialAlpha = 0f, animationSpec = tween(durationMillis = 500)) + expandIn()
    ) {
        Column(
            Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.5f),
            horizontalAlignment = CenterHorizontally
        ) {

            AlbumsArt(
                data = albumArt,
                modifier = Modifier.padding(20.dp)
            ) {
                content(it)
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