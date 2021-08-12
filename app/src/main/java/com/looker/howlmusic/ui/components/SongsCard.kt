package com.looker.howlmusic.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.looker.howlmusic.model.Song
import com.looker.howlmusic.ui.theme.Typography
import com.looker.howlmusic.utils.Constants.artworkUri

@Composable
fun SongsCard(
    modifier: Modifier = Modifier,
    song: Song,
    onClick: () -> Unit,
) {
    SongsItem(modifier = modifier.padding(10.dp), song = song, onClick = onClick)
}

@Composable
private fun SongsItem(
    modifier: Modifier = Modifier,
    song: Song,
    onClick: () -> Unit,
) {

    Card(
        modifier = modifier.fillMaxWidth(),
        shape = MaterialTheme.shapes.small,
        elevation = 0.dp
    ) {
        SongsItem(song = song, imageSize = 70.dp, onClick = onClick)
    }
}

@Composable
fun SongsItem(
    modifier: Modifier = Modifier,
    song: Song,
    imageSize: Dp,
    onClick: () -> Unit,
) {

    Row(
        modifier
            .background(MaterialTheme.colors.background)
            .clickable(onClick = onClick),
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        HowlImage(
            data = song.albumId.artworkUri,
            modifier = Modifier.size(imageSize),
            size = 50,
            shape = MaterialTheme.shapes.small
        )
        SongsItemText(song = song)
    }
}

@Composable
fun SongsItemText(modifier: Modifier = Modifier, song: Song) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(10.dp),
        horizontalAlignment = Alignment.Start
    ) {
        WrappedText(text = song.songName)
        WrappedText(text = song.artistName, style = Typography.body2)
    }
}