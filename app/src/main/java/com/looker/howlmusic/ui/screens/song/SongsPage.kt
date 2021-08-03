package com.looker.howlmusic.ui.screens.song

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import com.looker.howlmusic.model.Song
import com.looker.howlmusic.ui.composables.AlbumsArt
import com.looker.howlmusic.ui.composables.BodyText
import com.looker.howlmusic.ui.composables.HeaderText

@Composable
fun SongsList(songsList: MutableList<Song>) {
    LazyColumn(contentPadding = PaddingValues(bottom = 60.dp)) {
        items(songsList) { song ->
            SongsItem(song, modifier = Modifier.padding(10.dp))
        }
    }
}

@Composable
fun SongsItem(
    song: Song,
    modifier: Modifier = Modifier,
) {
    val cardHeight =
        (LocalContext.current.resources.displayMetrics.heightPixels / 12).dp / LocalDensity.current.density

    Card(
        modifier = modifier.fillMaxWidth(),
        shape = MaterialTheme.shapes.small
    ) {
        Row(
            Modifier
                .background(MaterialTheme.colors.background)
                .clickable { Log.e("Click", "${song.albumArtUri}") },
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ) {
            AlbumsArt(
                data = song.albumArtUri,
                modifier = Modifier.size(cardHeight)
            )
            Column {
                HeaderText(
                    text = song.songName
                )
                BodyText(
                    text = song.artistName
                )
            }
        }
    }
}