package com.represa.quoty.ui

import androidx.annotation.StringRes
import androidx.compose.foundation.Icon
import androidx.compose.foundation.Text
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.vector.VectorAsset
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import androidx.navigation.compose.*
import com.represa.quoty.R
import com.represa.quoty.ui.screen.NewQuote
import com.represa.quoty.ui.screen.QuoteDetail
import com.represa.quoty.ui.screen.Search

sealed class BottomNavigationScreens(val route: String, @StringRes val resourceId: Int, val icon: VectorAsset) {
    object Search : BottomNavigationScreens("Search", R.string.search_screen_route, Icons.Filled.Search)
    object NewQuote : BottomNavigationScreens("NewQuote", R.string.new_quote_screen_route, Icons.Filled.AddCircle)
    object Profile : BottomNavigationScreens("Profile", R.string.profile_screen_route, Icons.Filled.AccountCircle)
}

@Composable
fun MultiBottomNavApp() {

    val navController = rememberNavController()

    val bottomNavigationItems = listOf(
        BottomNavigationScreens.Search,
        BottomNavigationScreens.NewQuote,
        BottomNavigationScreens.Profile
    )
    Scaffold(
        bottomBar = {
            SpookyAppBottomNavigation(navController, bottomNavigationItems)
        },
    ) {
        MainScreenNavigationConfigurations(navController)
    }
}



@Composable
private fun MainScreenNavigationConfigurations(
    navController: NavHostController
) {
    NavHost(navController, startDestination = BottomNavigationScreens.Search.route) {
        composable(BottomNavigationScreens.Search.route) {
            Search(navController)
        }
        composable(BottomNavigationScreens.NewQuote.route) {
            NewQuote()
        }
        composable(BottomNavigationScreens.Profile.route) {
            NewQuote()
        }
        composable("quoteDetail/{id}"){ backStackEntry ->
            QuoteDetail(backStackEntry.arguments?.getString("id"))
        }
    }
}

@Composable
private fun SpookyAppBottomNavigation(
    navController: NavHostController,
    items: List<BottomNavigationScreens>
) {
    BottomNavigation {
        val currentRoute = currentRoute(navController)
        items.forEach { screen ->
            BottomNavigationItem(
                icon = { Icon(screen.icon) },
                label = { Text(stringResource(id = screen.resourceId)) },
                selected = currentRoute == screen.route,
                alwaysShowLabels = true, // This show the title for the unselected items
                onClick = {
                    // This if check gives us a "singleTop" behavior where we do not create a
                    // second instance of the composable if we are already on that destination
                    if (currentRoute != screen.route) {
                        navController.navigate(screen.route)
                    }
                }
            )
        }
    }
}

@Composable
private fun currentRoute(navController: NavHostController): String? {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    return navBackStackEntry?.arguments?.getString(KEY_ROUTE)
}