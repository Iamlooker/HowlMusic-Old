package com.looker.howlmusic.ui.home

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.google.accompanist.insets.LocalWindowInsets
import com.google.accompanist.insets.rememberInsetsPaddingValues
import com.looker.howlmusic.model.Song
import com.looker.howlmusic.ui.components.SongsCard
import com.looker.howlmusic.viewmodels.SongsViewModel

@Composable
fun Songs(viewModel: SongsViewModel = viewModel()) {
    val songsList = viewModel.songsList

    SongsList(songsList = songsList)
}

@Composable
fun SongsList(songsList: MutableList<Song>) {

    LazyColumn(
        contentPadding = rememberInsetsPaddingValues(
            insets = LocalWindowInsets.current.systemBars,
            applyTop = true
        )
    ) {
        items(songsList) { song ->
            SongsCard(modifier = Modifier.padding(10.dp), song = song) {

            }
        }
    }
}
