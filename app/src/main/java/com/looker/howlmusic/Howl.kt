package com.looker.howlmusic

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.insets.navigationBarsPadding
import com.looker.howlmusic.ui.components.BottomNavigation
import com.looker.howlmusic.ui.home.HomeSections
import com.looker.howlmusic.ui.theme.HowlMusicTheme

@Composable
fun HowlApp() {

    val navController = rememberNavController()
    val items = remember { HomeSections.values() }

    HowlMusicTheme {
        Scaffold(
            bottomBar = {
                BottomNavigation(navController = navController, items = items)
            },
            modifier = Modifier.navigationBarsPadding()
        ) { innerPadding ->
            Box(modifier = Modifier.padding(innerPadding)) {
                HowlNavGraph(navController)
            }
        }
    }
}