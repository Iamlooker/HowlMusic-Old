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
import com.looker.howlmusic.MainDestinations.ON_BOARD
import com.looker.howlmusic.ui.albumsdetails.AlbumsDetails
import com.looker.howlmusic.ui.home.HomeSections
import com.looker.howlmusic.ui.home.addHomeGraph
import com.looker.howlmusic.ui.onboarding.OnBoardingPage

object MainDestinations {
    const val ON_BOARD = "onBoard"
    const val HOME_ROUTE = "home"
    const val ALBUMS_DETAILS_ROUTE = "albumsDetails"
    const val ALBUM_ID = "albumId"
    const val ALBUM_NAME = "albumName"
    const val ARTIST_NAME = "artistName"
}

@Composable
fun HowlNavGraph(
    modifier: Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: String = ON_BOARD,
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ) {

        composable(ON_BOARD) {
            OnBoardingPage(navController = navController)
        }

        navigation(
            route = MainDestinations.HOME_ROUTE,
            startDestination = HomeSections.ALBUMS.route,
        ) {
            addHomeGraph(
                onAlbumClicked = { albumId, albumName, artistName, from: NavBackStackEntry ->
                    if (from.lifecycleIsResumed()) {
                        navController
                            .navigate("${MainDestinations.ALBUMS_DETAILS_ROUTE}/$albumId/$albumName/$artistName")
                    }
                }
            )
        }

        composable(
            "${MainDestinations.ALBUMS_DETAILS_ROUTE}/{$ALBUM_ID}/{$ALBUM_NAME}/{$ARTIST_NAME}",
            arguments = listOf(
                navArgument(ALBUM_ID) { type = NavType.LongType },
                navArgument(ALBUM_NAME) { type = NavType.StringType },
                navArgument(ARTIST_NAME) { type = NavType.StringType },
            )
        ) { backStackEntry ->
            val arguments = requireNotNull(backStackEntry.arguments)
            AlbumsDetails(
                albumId = arguments.getLong(ALBUM_ID),
                albumName = arguments.getString(ALBUM_NAME),
                artistName = arguments.getString(ARTIST_NAME),
                upPress = { navController.navigateUp() }
            )
        }
    }
}

fun NavBackStackEntry.lifecycleIsResumed() =
    this.lifecycle.currentState == Lifecycle.State.RESUMED