package com.looker.howlmusic.ui.albumsdetails

import android.net.Uri
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.TweenSpec
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.google.accompanist.insets.statusBarsHeight
import com.looker.howlmusic.model.Song
import com.looker.howlmusic.ui.components.HowlImage
import com.looker.howlmusic.ui.components.Up
import com.looker.howlmusic.ui.components.WrappedText
import com.looker.howlmusic.ui.home.SongsList
import com.looker.howlmusic.ui.theme.Typography
import com.looker.howlmusic.utils.Constants.artworkUri
import com.looker.howlmusic.utils.Constants.fadeInDuration
import com.looker.howlmusic.utils.rememberDominantColorState
import com.looker.howlmusic.viewmodels.AlbumsViewModel
import kotlinx.coroutines.launch

@Composable
fun AlbumsDetails(
    albumId: Long,
    albumName: String?,
    artistName: String?,
    upPress: () -> Unit = {},
    viewModel: AlbumsViewModel = viewModel(),
) {

    Up(iconTint = Color.Black, upPress = upPress)

    val albumArtUri = albumId.artworkUri

    AlbumsDetails(
        albumArtUri = albumArtUri,
        albumName = albumName,
        artistName = artistName,
        list = viewModel.getSongList(albumId)
    )

}

@Composable
private fun AlbumsDetails(
    albumArtUri: Uri,
    albumName: String?,
    artistName: String?,
    list: MutableList<Song>,
) {
    AlbumsView(
        albumArtUri = albumArtUri,
        albumName = albumName,
        artistName = artistName,
        list = list
    )
}

@Composable
fun AlbumsView(
    albumArtUri: Uri,
    albumName: String?,
    artistName: String?,
    list: MutableList<Song>,
) {
    val backgroundGradient = rememberDominantColorState()

    LaunchedEffect(albumArtUri) {
        launch {
            backgroundGradient.updateColorsFromImageUrl(albumArtUri.toString())
        }
    }

    val animateBackgroundGradient by animateColorAsState(
        targetValue = backgroundGradient.color,
        animationSpec = TweenSpec(
            durationMillis = fadeInDuration
        )
    )
    AlbumsHeaderBackground(animateBackgroundGradient = animateBackgroundGradient)
    {
        AlbumsHeader(
            albumArtUri = albumArtUri,
            albumName = albumName,
            artistName = artistName
        )
        AlbumsDetailsList(list)
    }

}

@Composable
fun AlbumsHeader(
    albumArtUri: Uri,
    albumName: String?,
    artistName: String?,
) {

    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(15.dp)
    ) {
        HowlImage(data = albumArtUri)
        AlbumsHeaderText(albumName = albumName, artistName = artistName)
    }
}

@Composable
fun AlbumsHeaderBackground(
    animateBackgroundGradient: Color,
    content: @Composable () -> Unit,
) {
    Column {

        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .statusBarsHeight(15.dp)
                .background(animateBackgroundGradient)
        )

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .background(
                    Brush.verticalGradient(
                        listOf(
                            animateBackgroundGradient,
                            Color.Transparent
                        )
                    )
                )

        ) {
            Column { content() }
        }
    }
}


@Composable
fun AlbumsHeaderText(albumName: String?, artistName: String?) {
    Column(
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        WrappedText(
            text = albumName,
            style = Typography.h5
        )
        WrappedText(
            text = artistName,
            style = Typography.body2
        )
    }
}

@Composable
fun AlbumsDetailsList(
    list: MutableList<Song>,
) {
    SongsList(songsList = list) {

    }
}