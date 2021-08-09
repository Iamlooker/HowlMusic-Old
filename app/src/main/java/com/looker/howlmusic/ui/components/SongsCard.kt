package com.looker.howlmusic.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.looker.howlmusic.model.Song

@Composable
fun SongsCard(
    modifier: Modifier = Modifier,
    song: Song,
    onClick: () -> Unit,
) {
    val cardHeight =
        (LocalContext.current.resources.displayMetrics.heightPixels / 12).dp / LocalDensity.current.density

    SongsCard(modifier = modifier, song = song, cardHeight = cardHeight, onClick = onClick)
}

@Composable
private fun SongsCard(
    modifier: Modifier = Modifier,
    song: Song,
    cardHeight: Dp,
    onClick: () -> Unit,
) {

    Card(
        modifier = modifier
            .fillMaxWidth(),
        shape = MaterialTheme.shapes.small,
        elevation = 0.dp
    ) {
        SongsItem(song = song, imageSize = cardHeight, onClick = onClick)
    }
}

@Composable
fun SongsItem(
    song: Song,
    imageSize: Dp,
    onClick: () -> Unit,
) {
    Row(
        Modifier
            .background(MaterialTheme.colors.background)
            .clickable(onClick = onClick),
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically
    ) {
        HowlImage(
            data = song.albumArtUri,
            modifier = Modifier.size(imageSize),
            shape = MaterialTheme.shapes.small
        )
        SongsItemText(song = song)
    }
}

@Composable
fun SongsItemText(song: Song) {
    Column(
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        HeaderText(
            text = song.songName
        )
        BodyText(
            text = song.artistName
        )
    }
}