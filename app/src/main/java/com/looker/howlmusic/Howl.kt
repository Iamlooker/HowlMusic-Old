package com.looker.howlmusic

import android.app.Application
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.looker.howlmusic.model.Album
import com.looker.howlmusic.model.Song
import com.looker.howlmusic.navigation.BottomNavHost
import com.looker.howlmusic.navigation.Screens
import com.looker.howlmusic.ui.composables.BottomNavigation
import com.looker.howlmusic.ui.theme.HowlMusicTheme

class Howl : Application()

@Composable
fun Howl(
    albumsList: MutableList<Album>,
    songsList: MutableList<Song>,
) {

    val navController = rememberNavController()

    HowlMusicTheme {
        Scaffold(
            bottomBar = {
                BottomNavigation(navController = navController, items = Screens.Items.items)
            }
        ) { innerPadding ->
            Box(modifier = Modifier.padding(innerPadding)) {
                BottomNavHost(
                    navController = navController,
                    songsList = songsList,
                    albumsList = albumsList
                )
            }
        }
    }
}