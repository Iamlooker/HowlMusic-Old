package com.looker.howlmusic.ui.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.google.accompanist.insets.LocalWindowInsets
import com.google.accompanist.insets.rememberInsetsPaddingValues
import com.looker.howlmusic.model.Song
import com.looker.howlmusic.ui.components.MiniPlayer
import com.looker.howlmusic.ui.components.SongsCard
import com.looker.howlmusic.viewmodels.SongsViewModel

@Composable
fun Songs(viewModel: SongsViewModel = viewModel()) {

    val songsList = viewModel.songsList

    SongsList(songsList = songsList)
}

@Composable
fun SongsList(songsList: List<Song>, viewModel: SongsViewModel = viewModel()) {

    val currentSong by viewModel.playingSong.collectAsState()

    Column {
        LazyColumn(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.Center,
            contentPadding = rememberInsetsPaddingValues(
                insets = LocalWindowInsets.current.systemBars,
                applyTop = true
            )
        ) {
            items(songsList) { song ->
                SongsCard(modifier = Modifier.fillMaxWidth(), song = song) {
                    viewModel.addMediaItems(song)
                    viewModel.setCurrentSong(song)
                    viewModel.playSongs()
                }
            }
        }
        MiniPlayer(song = currentSong, player = viewModel.player)
    }
}
