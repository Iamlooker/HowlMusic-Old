package com.looker.howlmusic

import android.Manifest
import android.app.Application
import android.content.pm.PackageManager
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.looker.howlmusic.model.Album
import com.looker.howlmusic.model.Song
import com.looker.howlmusic.navigation.BottomNavHost
import com.looker.howlmusic.navigation.Screens
import com.looker.howlmusic.ui.composables.BottomNavigation
import com.looker.howlmusic.ui.composables.TopAppBar
import com.looker.howlmusic.ui.theme.HowlMusicTheme
import com.looker.howlmusic.viewModels.HowlViewModel

class Howl : Application()

@Composable
fun Howl(albumsList: MutableList<Album>, songsList: MutableList<Song>) {

    val navController = rememberNavController()

    HowlMusicTheme {
        Scaffold(
            topBar = {
                TopAppBar()
            },
            bottomBar = {
                BottomNavigation(navController = navController, items = Screens.Items.items)
            }
        ) {
            BottomNavHost(
                navController = navController,
                songsList = songsList,
                albumsList = albumsList
            )
        }
    }
}