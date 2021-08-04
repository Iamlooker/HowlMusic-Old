package com.looker.howlmusic

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.Lifecycle
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navArgument
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import com.looker.howlmusic.MainDestinations.ALBUM_ID
import com.looker.howlmusic.MainDestinations.ALBUM_NAME
import com.looker.howlmusic.MainDestinations.ARTIST_NAME
import com.looker.howlmusic.ui.albumdetail.DetailsMain
import com.looker.howlmusic.ui.home.HomeSections
import com.looker.howlmusic.ui.home.addHomeGraph

object MainDestinations {
    const val ON_BOARD_ROUTE = "on_board"
    const val HOME_ROUTE = "home"
    const val ALBUMS_DETAILS_ROUTE = "album_details"
    const val ALBUM_ID = "album_id"
    const val ALBUM_NAME = "album_name"
    const val ARTIST_NAME = "artist_name"
}

@Composable
fun HowlNavGraph(
    modifier: Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: String = MainDestinations.HOME_ROUTE,
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ) {

        navigation(
            route = MainDestinations.HOME_ROUTE,
            startDestination = HomeSections.ALBUMS.route,
        ) {
            addHomeGraph(navController)
        }

        composable(
            "${MainDestinations.ALBUMS_DETAILS_ROUTE}/{$ALBUM_NAME}/{$ARTIST_NAME}/{$ALBUM_ID}",
            arguments = listOf(
                navArgument(ALBUM_NAME) { type = NavType.StringType },
                navArgument(ARTIST_NAME) { type = NavType.StringType },
                navArgument(ALBUM_ID) { type = NavType.LongType },
            )
        ) { backStackEntry ->
            val arguments = requireNotNull(backStackEntry.arguments)
            DetailsMain(
                albumName = arguments.getString(ALBUM_NAME),
                artistName = arguments.getString(ARTIST_NAME),
                albumId = arguments.getLong(ALBUM_ID),
                upPress = { navController.navigateUp() }
            )
        }
    }
}

fun NavBackStackEntry.lifecycleIsResumed() =
    this.lifecycle.currentState == Lifecycle.State.RESUMED