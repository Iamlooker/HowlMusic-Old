package com.looker.howlmusic.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.PlayArrow
import androidx.compose.material.icons.outlined.SkipNext
import androidx.compose.material.icons.outlined.SkipPrevious
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.looker.howlmusic.model.Song

@Composable
fun MiniPlayer(modifier: Modifier = Modifier, song: Song) {
    Surface(
        shape = MaterialTheme.shapes.large,
        color = MaterialTheme.colors.background,
        modifier = modifier.padding()
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {
            SongsCard(
                song = song,
                onClick = { /*OPEN PLAYER*/ },
                cardHeight = 45.dp
            )
            PlaybackControls()
        }
    }
}

@Composable
fun PlaybackControls(
    showPrevious: Boolean = false,
    showNext: Boolean = true,
) {
    if (showPrevious) {
        IconButton(onClick = { /*Previous*/ }) {
            Icon(
                imageVector = Icons.Outlined.SkipPrevious,
                contentDescription = "Previous Song",
                modifier = Modifier.size(32.dp),
                tint = MaterialTheme.colors.onBackground
            )
        }
    }
    IconButton(onClick = { /*PLAY*/ }) {
        Icon(
            imageVector = Icons.Outlined.PlayArrow,
            contentDescription = "Play Song",
            modifier = Modifier.size(32.dp),
            tint = MaterialTheme.colors.onBackground
        )
    }
    if (showNext) {
        IconButton(onClick = { /*NEXT*/ }) {
            Icon(
                imageVector = Icons.Outlined.SkipNext,
                contentDescription = "Next Song",
                modifier = Modifier.size(32.dp),
                tint = MaterialTheme.colors.onBackground
            )
        }
    }
}