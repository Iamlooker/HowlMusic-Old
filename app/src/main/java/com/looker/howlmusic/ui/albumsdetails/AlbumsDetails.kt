package com.looker.howlmusic.ui.albumsdetails

import android.content.ContentUris
import android.net.Uri
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.TweenSpec
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.google.accompanist.insets.statusBarsHeight
import com.looker.howlmusic.ui.components.BodyText
import com.looker.howlmusic.ui.components.HeaderText
import com.looker.howlmusic.ui.components.HowlImage
import com.looker.howlmusic.ui.components.Up
import com.looker.howlmusic.ui.home.SongsList
import com.looker.howlmusic.ui.theme.Typography
import com.looker.howlmusic.utils.Constants
import com.looker.howlmusic.utils.Constants.fadeInDuration

@Composable
fun AlbumsDetails(
    albumId: Long,
    albumName: String?,
    artistName: String?,
    upPress: () -> Unit = {},
) {

    Up(upPress = upPress)

    val albumArtUri = ContentUris.withAppendedId(Constants.artworkUri, albumId)

    AlbumsDetails(albumArtUri = albumArtUri, albumName = albumName, artistName = artistName)

}

@Composable
private fun AlbumsDetails(
    albumArtUri: Uri,
    albumName: String?,
    artistName: String?,
) {
    Column {
        AlbumsHeaderGradient(albumArtUri = albumArtUri,
            albumName = albumName,
            artistName = artistName)
        AlbumsDetailsList()
    }
}

@Composable
fun AlbumsHeaderGradient(
    albumArtUri: Uri,
    albumName: String?,
    artistName: String?,
) {

    var backgroundGradient by remember {
        mutableStateOf(Color.Transparent)
    }

    val animateBackgroundGradient by animateColorAsState(
        targetValue = backgroundGradient,
        animationSpec = TweenSpec(
            durationMillis = fadeInDuration
        )
    )

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
                .fillMaxHeight(0.5f)
                .background(
                    Brush.verticalGradient(
                        listOf(
                            animateBackgroundGradient,
                            Color.Transparent
                        )
                    )
                )

        ) {
            AlbumsHeader(
                albumArtUri = albumArtUri,
                albumName = albumName,
                artistName = artistName,
                getColor = { vibrantColor ->
                    backgroundGradient = vibrantColor
                }
            )
        }
    }
}

@Composable
fun AlbumsHeader(
    albumArtUri: Uri,
    albumName: String?,
    artistName: String?,
    getColor: (Color) -> Unit,
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(15.dp)
    ) {
        HowlImage(data = albumArtUri) { vibrantColor ->
            getColor(vibrantColor.copy(0.4f))
        }
        AlbumsHeaderText(albumName = albumName, artistName = artistName)
    }
}

@Composable
fun AlbumsHeaderText(albumName: String?, artistName: String?) {
    Column(
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        HeaderText(
            text = albumName,
            style = Typography.h5
        )
        BodyText(
            text = artistName
        )
    }
}

@Composable
fun AlbumsDetailsList() {
    SongsList(songsList = mutableListOf())
}