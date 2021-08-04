package com.looker.howlmusic.ui.albumdetail

import android.content.ContentUris
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandIn
import androidx.compose.animation.fadeIn
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.google.accompanist.insets.statusBarsHeight
import com.google.accompanist.insets.statusBarsPadding
import com.looker.howlmusic.R
import com.looker.howlmusic.ui.components.BodyText
import com.looker.howlmusic.ui.components.HeaderText
import com.looker.howlmusic.ui.components.HowlImage
import com.looker.howlmusic.ui.theme.Typography
import com.looker.howlmusic.utils.Constants.artworkUri

@Composable
fun DetailsMain(
    albumName: String?,
    artistName: String?,
    albumId: Long,
    upPress: () -> Unit = {},
) {
    var backgroundGradient by remember {
        mutableStateOf(Color.Transparent)
    }

    Up {
        upPress()
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
    albumName: String?,
    artistName: String?,
    albumId: Long,
    useColor: (Color) -> Unit,
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

            HowlImage(
                data = albumArt,
                modifier = Modifier.padding(20.dp)
            ) {
                useColor(it)
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

@Composable
fun Up(upPress: () -> Unit) {
    IconButton(
        onClick = upPress,
        modifier = Modifier
            .statusBarsPadding()
            .padding(horizontal = 16.dp, vertical = 10.dp)
            .size(36.dp)
            .background(
                color = MaterialTheme.colors.surface.copy(alpha = 0.32f),
                shape = CircleShape
            )
    ) {
        Icon(
            imageVector = Icons.Default.ArrowBack,
            tint = MaterialTheme.colors.primary,
            contentDescription = stringResource(R.string.label_back)
        )
    }

}