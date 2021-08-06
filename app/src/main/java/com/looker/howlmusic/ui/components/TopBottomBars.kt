package com.looker.howlmusic.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.currentBackStackEntryAsState
import com.looker.howlmusic.R
import com.looker.howlmusic.ui.home.HomeSections
import com.looker.howlmusic.ui.theme.Typography


//Unused for Time being
@Composable
fun TopAppBar() {
    TopAppBar(
        title = {
            HeaderText(
                modifier = Modifier.fillMaxWidth(),
                text = stringResource(id = R.string.app_name),
                style = Typography.h5,
                textAlign = TextAlign.Center
            )
        },
        backgroundColor = MaterialTheme.colors.background,
        contentColor = MaterialTheme.colors.onBackground
    )
}

@Composable
fun BottomNavigation(navController: NavController, items: Array<HomeSections>) {

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    val sections = remember { HomeSections.values() }
    val routes = remember { sections.map { it.route } }

    if (currentRoute in routes) {
        BottomNavigation(
            backgroundColor = MaterialTheme.colors.background,
            contentColor = MaterialTheme.colors.primary,
        ) {
            val currentDestination = navBackStackEntry?.destination
            items.forEach { screen ->
                BottomNavigationItem(
                    icon = { Icon(screen.icon, contentDescription = null) },
                    label = { Text(stringResource(screen.title)) },
                    selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true,
                    alwaysShowLabel = false,
                    selectedContentColor = MaterialTheme.colors.primary,
                    unselectedContentColor = MaterialTheme.colors.surface,
                    onClick = {
                        navController.navigate(screen.route) {
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                )
            }
        }
    }
}