package com.represa.quoty.util.navigation.views

import android.os.Bundle
import androidx.compose.foundation.Text
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.onCommit
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigate
import androidx.navigation.compose.rememberNavController
import com.represa.quoty.util.navigation.Screen

//This is the composable that manage the navigation bar for the Dashboard View
@Composable
fun DashboardTab(navState: MutableState<Bundle>) {
    val navController = rememberNavController()

    onCommit {
        val callback = NavController.OnDestinationChangedListener { controller, _, _ ->
            navState.value = controller.saveState() ?: Bundle()
        }
        navController.addOnDestinationChangedListener(callback)
        navController.restoreState(navState.value)

        onDispose {
            navController.removeOnDestinationChangedListener(callback)
            // workaround for issue where back press is intercepted
            // outside this tab, even after this Composable is disposed
            navController.enableOnBackPressed(false)
        }
    }

    //With NavHost we declare the diferents states of our views. In this case we have:
    //Screen.Dashboard -> when we show the Dashboard view
    //Screen.DashboardDetail -> when we click in the Screen.Dashboard button we'll navigate to it
    NavHost(
        navController = navController,
        startDestination = Screen.NewQuote
    ) {
        composable(Screen.NewQuote) { Dashboard(navController) }
        composable(Screen.NewQuoteDetail) {
            Text("Some Dashboard detail")
        }
    }
}

@Composable
fun Dashboard(navController: NavController) {
    Column(modifier = Modifier.fillMaxSize().then(Modifier.padding(8.dp))) {
        Text(text = Screen.NewQuote.title)
        Button(
            content = { Text("Open Dashboard Detail") },
            onClick = {
                navController.navigate(Screen.NewQuoteDetail)
            }
        )
    }
}