package com.looker.howlmusic.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navArgument
import com.looker.howlmusic.model.Album
import com.looker.howlmusic.model.Song
import com.looker.howlmusic.ui.screens.album.AlbumsList
import com.looker.howlmusic.ui.screens.album.DetailsMain
import com.looker.howlmusic.ui.screens.song.SongsList

@Composable
fun BottomNavHost(
    navController: NavHostController,
    songsList: MutableList<Song>,
    albumsList: MutableList<Album>,
) {
    NavHost(
        navController,
        startDestination = Screens.Albums.route
    ) {

        composable(Screens.Songs.route) {
            SongsList(
                songsList = songsList
            )
        }
        composable(Screens.Albums.route) {
            AlbumsList(
                albumsList = albumsList,
                navController = navController
            )
        }
        composable(
            Screens.AlbumsDetail.route,
            arguments = listOf(
                navArgument("albumName") {
                    type = NavType.StringType
                },
                navArgument("artistName") {
                    type = NavType.StringType
                },
                navArgument("albumId") {
                    type = NavType.LongType
                },
            )
        ) {
            DetailsMain(
                albumName = it.arguments?.getString("albumName"),
                artistName = it.arguments?.getString("artistName"),
                albumId = it.arguments!!.getLong("albumId")
            )
        }
    }
}

@Composable
fun AlbumsNavHost(
    albumsList: MutableList<Album>,
    navController: NavHostController,
) {
    NavHost(
        navController = navController,
        startDestination = AlbumsScreens.AlbumsMain.route
    ) {
        composable(AlbumsScreens.AlbumsMain.route) {
            AlbumsList(navController = navController, albumsList = albumsList)
        }
        composable(
            AlbumsScreens.AlbumsDetail.route,
            arguments = listOf(
                navArgument("albumName") {
                    type = NavType.StringType
                },
                navArgument("artistName") {
                    type = NavType.StringType
                },
                navArgument("albumId") {
                    type = NavType.LongType
                },
            )
        ) {
            it.arguments?.getLong("albumId")?.let { albumId ->
                DetailsMain(
                    albumName = it.arguments?.getString("albumName"),
                    artistName = it.arguments?.getString("albumName"),
                    albumId = albumId
                )
            }
        }
    }
}