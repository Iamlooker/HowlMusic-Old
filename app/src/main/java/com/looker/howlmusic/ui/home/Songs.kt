package com.looker.howlmusic.ui.home

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import com.google.accompanist.insets.LocalWindowInsets
import com.google.accompanist.insets.rememberInsetsPaddingValues
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.SimpleExoPlayer
import com.looker.howlmusic.model.Song
import com.looker.howlmusic.ui.components.SongsCard
import com.looker.howlmusic.viewmodels.SongsViewModel

@Composable
fun Songs(viewModel: SongsViewModel = viewModel()) {

    val songsList = viewModel.songsList

    SongsList(songsList = songsList)
}

@Composable
fun SongsList(songsList: List<Song>) {
    val player = SimpleExoPlayer.Builder(LocalContext.current).build()

    LazyColumn(
        contentPadding = rememberInsetsPaddingValues(
            insets = LocalWindowInsets.current.systemBars,
            applyTop = true
        )
    ) {
        items(songsList) { song ->
            SongsCard(modifier = Modifier.fillMaxWidth(), song = song) {
                player.clearMediaItems()
                player.setMediaItem(MediaItem.fromUri(it.songUri))
                player.prepare()
                player.play()
            }
        }
    }
}
