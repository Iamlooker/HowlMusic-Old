package com.looker.howlmusic.ui.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Pause
import androidx.compose.material.icons.outlined.PlayArrow
import androidx.compose.material.icons.outlined.SkipNext
import androidx.compose.material.icons.outlined.SkipPrevious
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.google.android.exoplayer2.SimpleExoPlayer
import com.looker.howlmusic.model.Song

@Composable
fun MiniPlayer(modifier: Modifier = Modifier, player: SimpleExoPlayer, song: Song) {
    Surface(
        shape = MaterialTheme.shapes.large,
        color = MaterialTheme.colors.background,
        modifier = modifier
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            SongsCard(
                modifier = Modifier.weight(1f),
                song = song,
                onClick = { /*OPEN PLAYER*/ },
            )
            PlaybackControls(player = player)
        }
    }
}

@Composable
fun PlaybackControls(
    showPrevious: Boolean = false,
    showNext: Boolean = true,
    player: SimpleExoPlayer,
) {

    fun playOrPause() = if (player.isPlaying) player.pause() else player.play()
    fun playNext() = if (player.hasNextWindow()) player.seekToNext() else player.pause()
    val playOrPauseIcon by remember {
        mutableStateOf(if (player.isPlaying) Icons.Outlined.Pause
        else Icons.Outlined.PlayArrow)
    }


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
    IconButton(onClick = { playOrPause() }) {
        Icon(
            imageVector = playOrPauseIcon,
            contentDescription = "Play Song",
            modifier = Modifier.size(32.dp),
            tint = MaterialTheme.colors.onBackground
        )
    }
    if (showNext) {
        IconButton(onClick = { playNext() }) {
            Icon(
                imageVector = Icons.Outlined.SkipNext,
                contentDescription = "Next Song",
                modifier = Modifier.size(32.dp),
                tint = MaterialTheme.colors.onBackground
            )
        }
    }
}