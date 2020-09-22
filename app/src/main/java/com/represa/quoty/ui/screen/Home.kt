package com.represa.quoty.ui.screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumnFor
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.represa.quoty.ui.components.MainCard
import com.represa.quoty.ui.viewmodel.MainViewModel

@Composable
fun Home(viewModel: MainViewModel) {

    Scaffold(
            topBar = { Toolbar() }) {

        LazyColumnFor(
            items = viewModel.prueba,
            itemContent = { MainCard() }
        )
    }
}

@Composable
private fun Toolbar() {
    TopAppBar(
            modifier = Modifier.fillMaxWidth(),
            backgroundColor = MaterialTheme.colors.background
    ) {
        Row(
                modifier = Modifier.fillMaxWidth()
                        .padding(horizontal = 10.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
        ) {
            /*Icon(
                    imageResource(id = R.drawable.ic_baseline_4k_24),
                    modifier = Modifier.preferredSize(24.dp)
            )
            Box(
                    modifier = Modifier.padding(12.dp),
                    gravity = ContentGravity.Center
            ) {
                Icon(vectorResource(id = R.drawable.ic_baseline_4k_24))
            }
            Icon(
                    imageResource(id = R.drawable.ic_baseline_4k_24),
                    modifier = Modifier.preferredSize(24.dp)
            )*/
        }
    }
}
