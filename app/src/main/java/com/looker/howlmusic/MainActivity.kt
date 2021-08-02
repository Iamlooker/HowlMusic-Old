package com.looker.howlmusic

import android.content.pm.PackageManager
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.core.content.ContextCompat
import com.looker.howlmusic.data.AlbumsData
import com.looker.howlmusic.data.SongsData
import com.looker.howlmusic.model.Album
import com.looker.howlmusic.model.Song
import com.looker.howlmusic.ui.screens.onboard.OnBoardingPage
import com.looker.howlmusic.utils.Constants.permission


class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val albums = AlbumsData(this).getAlbumList()
        val songs = SongsData(this).getSongList()

        val permissionGranted: Boolean by mutableStateOf(
            ContextCompat.checkSelfPermission(
                this, permission[0]
            ) == PackageManager.PERMISSION_GRANTED)


        setContent {
            DisplayWhat(
                permissionGranted = permissionGranted,
                albumsList = albums,
                songsList = songs
            )
        }
    }
}

@Composable
fun DisplayWhat(
    permissionGranted: Boolean,
    albumsList: MutableList<Album>,
    songsList: MutableList<Song>,
) {
    if (permissionGranted) Howl(albumsList = albumsList, songsList = songsList)
    else OnBoardingPage()
}