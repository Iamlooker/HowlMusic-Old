package com.looker.howlmusic.ui.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import com.google.accompanist.insets.LocalWindowInsets
import com.google.accompanist.insets.rememberInsetsPaddingValues
import com.google.android.exoplayer2.SimpleExoPlayer
import com.looker.howlmusic.model.Song
import com.looker.howlmusic.playback.PlaybackService
import com.looker.howlmusic.ui.components.MiniPlayer
import com.looker.howlmusic.ui.components.SongsCard
import com.looker.howlmusic.utils.Constants.isPlaying
import com.looker.howlmusic.utils.Extension.startNotification
import com.looker.howlmusic.viewmodels.SongsViewModel

@Composable
fun Songs(viewModel: SongsViewModel = viewModel()) {
    val context = LocalContext.current
    Songs(
        currentSong = viewModel.playingSong.collectAsState().value,
        songsList = viewModel.songsList,
        player = viewModel.player
    ) {
        isPlaying = true
        context.startNotification(isPlaying, PlaybackService())
        viewModel.setCurrentSong(it)
        viewModel.addMediaItems(it)
        viewModel.playSongs()
    }
}

@Composable
private fun Songs(
    modifier: Modifier = Modifier,
    currentSong: Song,
    songsList: List<Song>,
    player: SimpleExoPlayer,
    onSongClick: (Song) -> Unit,
) {
    Column(modifier = Modifier.fillMaxSize()) {
        SongsList(modifier = modifier.weight(1f),
            songsList = songsList,
            onSongClick = onSongClick)
        MiniPlayer(player = player, song = currentSong)
    }
}

@Composable
fun SongsList(
    modifier: Modifier = Modifier,
    songsList: List<Song>,
    onSongClick: (Song) -> Unit,
) {
    LazyColumn(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        contentPadding = rememberInsetsPaddingValues(
            insets = LocalWindowInsets.current.systemBars
        )
    ) {
        items(songsList) { song ->
            SongsCard(modifier = Modifier.fillMaxWidth(), song = song) { onSongClick(song) }
        }
    }
}
