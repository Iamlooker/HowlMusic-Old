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
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.looker.howlmusic.model.Song
import com.looker.howlmusic.ui.theme.Typography
import com.looker.howlmusic.utils.Constants.itemSize
import com.looker.howlmusic.utils.Constants.numberOfSongs
import com.looker.howlmusic.utils.Extension.artworkUri

@Composable
fun SongsCard(
    modifier: Modifier = Modifier,
    song: Song,
    onClick: (Song) -> Unit,
) {
    SongsItem(
        modifier = modifier.padding(10.dp),
        song = song,
        onClick = onClick
    )
}

@Composable
private fun SongsItem(
    modifier: Modifier = Modifier,
    song: Song,
    onClick: (Song) -> Unit,
) {

    val cardHeight = itemSize(LocalContext.current, true, numberOfSongs)

    Card(
        modifier = modifier,
        shape = MaterialTheme.shapes.small,
        elevation = 0.dp
    ) {
        Row(
            Modifier
                .background(MaterialTheme.colors.background)
                .clickable(onClick = { onClick(song) }),
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) { SongsItem(song = song, imageSize = cardHeight) }
    }
}

@Composable
fun SongsItem(
    song: Song,
    imageSize: Dp,
) {
    HowlImage(
        data = song.albumId.artworkUri,
        modifier = Modifier.size(imageSize),
        size = 50,
        shape = MaterialTheme.shapes.small
    )
    SongsItemText(song = song)
}

@Composable
fun SongsItemText(modifier: Modifier = Modifier, song: Song) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalAlignment = Alignment.Start
    ) {
        WrappedText(text = song.songName)
        WrappedText(text = song.artistName, style = Typography.caption)
    }
}