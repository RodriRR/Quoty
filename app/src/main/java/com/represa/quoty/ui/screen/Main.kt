package com.represa.quoty.ui.screen

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.Icon
import androidx.compose.foundation.Text
import androidx.compose.foundation.contentColor
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.preferredSize
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.savedinstancestate.savedInstanceState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import com.represa.quoty.R
import com.represa.quoty.ui.screen.HomeSection.Home
import com.represa.quoty.ui.viewmodel.MainViewModel

@Composable
fun Main(viewModel: MainViewModel) {
    val (currentSection, setCurrentSection) = savedInstanceState { Home }
    val navItems = HomeSection.values()
            .toList()

    Scaffold(
        topBar = {
        },
        bottomBar = {
            BottomBar(
                items = navItems,
                currentSection = currentSection,
                onSectionSelected = setCurrentSection,
            )
        }) { innerPadding ->
        val modifier = Modifier.padding(innerPadding)
        Crossfade(currentSection) { section ->
            when (section) {
                Home -> Home(viewModel)
                //HomeSection.Fav -> Login()
            }
        }
    }
}

@Composable
private fun BottomBar(
        items: List<HomeSection>,
        currentSection: HomeSection,
        onSectionSelected: (HomeSection) -> Unit,
) {
    /*BottomNavigation(
            modifier = Modifier.height(24.dp),
            backgroundColor = MaterialTheme.colors.background,
            contentColor = contentColor()
    ) {
        items.forEach { section ->

            val selected = section == currentSection

            val iconRes = section.icon

            BottomNavigationItem(
                    icon = {
                            Icon(
                                    vectorResource(id = iconRes),
                                    modifier = Modifier.preferredSize(24.dp)
                            )
                    },
                    selected = selected,
                    onClick = { onSectionSelected(section) },
                    alwaysShowLabels = false
            )
        }
    }*/
}

private enum class HomeSection(
        val icon: Int
) {
    Home(R.drawable.ic_baseline_4k_24),
    Fav(R.drawable.ic_baseline_4k_24)
}