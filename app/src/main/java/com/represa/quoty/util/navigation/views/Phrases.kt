package com.represa.quoty.util.navigation.views

import android.os.Bundle
import androidx.compose.foundation.Text
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumnFor
import androidx.compose.material.ListItem
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.onCommit
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.core.os.bundleOf
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigate
import androidx.navigation.compose.rememberNavController
import com.represa.quoty.util.navigation.Screen
import com.represa.quoty.util.navigation.phrases

//This is the composable that manage the navigation bar for the Dashboard View
@Composable
fun NavPhrasesTab(navState: MutableState<Bundle>) {
    val navController = rememberNavController()

    onCommit {
        val callback = NavController.OnDestinationChangedListener { navController, _, _ ->
            navState.value = navController.saveState() ?: Bundle()
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
    //Screen.Phrases -> when we show the Phrases view
    //Screen.PhraseDetail -> when we tap in a phrases into the Screen.Phrases
    NavHost(
        navController = navController,
        startDestination = Screen.Profile
    ) {
        composable(Screen.Profile) { Phrases(navController) }
        composable(Screen.ProfileDetails) { PhraseDetail(it.arguments?.get("phrase") as String) }
    }
}

@Composable
fun Phrases(navController: NavController? = null) {
    Column(modifier = Modifier.fillMaxSize().then(Modifier.padding(8.dp))) {
        LazyColumnFor(items = phrases) {
            if (navController != null) {
                ListItem(
                    text = { Text(text = it) },
                    modifier = Modifier.clickable(onClick = {
                        navController.navigate(
                            Screen.ProfileDetails,
                            bundleOf("phrase" to it)
                        )
                    })
                )
            } else {
                ListItem(text = { Text(text = it) })
            }
        }
    }
}


@Composable
fun PhraseDetail(phrase: String = "no phrase") {
    Text(text = phrase)
}