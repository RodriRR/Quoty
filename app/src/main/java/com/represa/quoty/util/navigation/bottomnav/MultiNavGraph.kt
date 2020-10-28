package com.represa.quoty.util.navigation.bottomnav

import android.os.Bundle
import androidx.compose.foundation.Icon
import androidx.compose.foundation.Text
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.runtime.savedinstancestate.Saver
import androidx.compose.runtime.savedinstancestate.rememberSavedInstanceState
import com.represa.quoty.ui.screen.SearchView
import com.represa.quoty.ui.theme.QuotyTheme
import com.represa.quoty.util.navigation.*
import com.represa.quoty.util.navigation.views.DashboardTab
import com.represa.quoty.util.navigation.views.NavPhrasesTab
import com.represa.quoty.util.navigation.views.ProfileTab

@Composable
fun MultiBottomNavApp() {
    BottomNavApp {
        MultiNavTabContent(screen = it)
    }
}

@Composable
fun MultiNavTabContent(screen: Screen) {
    val dashboardNavState = rememberSavedInstanceState(saver = NavStateSaver()) { mutableStateOf(Bundle()) }
    val phrasesNavState = rememberSavedInstanceState(saver = NavStateSaver()) { mutableStateOf(Bundle()) }
    when (screen) {
        Screen.Search    -> SearchView()
        Screen.NewQuote  -> DashboardTab(dashboardNavState)
        Screen.Profile   -> NavPhrasesTab(phrasesNavState)
        else             -> SearchView()
    }
}

@Composable
fun BottomNavApp(
    bodyContent: @Composable (Screen) -> Unit
) {
    var currentTab by rememberSavedInstanceState(saver = ScreenSaver()) { mutableStateOf(Screen.Search) }

    QuotyTheme {
        // A surface container using the 'background' color from the theme
        Surface(color = MaterialTheme.colors.background) {
            Scaffold(
                bodyContent = {
                    bodyContent(currentTab)
                },
                bottomBar = {
                    BottomNavigation {
                        BottomNavigationItem(
                            icon = { Icon(asset = Icons.Default.Search) },
                            label = { Text("Search") },
                            selected = currentTab == Screen.Search,
                            onClick = { currentTab = Screen.Search }
                        )
                        BottomNavigationItem(
                            icon = { Icon(asset = Icons.Default.AddCircle) },
                            label = { Text("New Quote") },
                            selected = currentTab == Screen.NewQuote,
                            onClick = { currentTab = Screen.NewQuote }
                        )
                        BottomNavigationItem(
                            icon = { Icon(asset = Icons.Default.AccountCircle) },
                            label = { Text("Profile") },
                            selected = currentTab == Screen.Profile,
                            onClick = { currentTab = Screen.Profile }
                        )
                    }
                }
            )
        }
    }
}

/**
 * Saver to save and restore the current tab across config change and process death.
 */
fun ScreenSaver(
): Saver<MutableState<Screen>, *> = Saver(
    save = { it.value.saveState() },
    restore = { mutableStateOf(Screen.restoreState(it)) }
)

fun NavStateSaver(): Saver<MutableState<Bundle>, out Any> = Saver(
    save = { it.value },
    restore = { mutableStateOf(it) }
)
