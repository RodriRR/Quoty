package com.represa.quoty.ui.screen

import androidx.compose.foundation.Box
import androidx.compose.foundation.ContentGravity
import androidx.compose.foundation.Icon
import androidx.compose.foundation.ScrollableColumn
import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import com.represa.quoty.R

@Composable
fun Home() {

    Scaffold(
            topBar = { Toolbar() }) {

        ScrollableColumn {

            Divider()

            Spacer(modifier = Modifier.height(10.dp))
        }
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
                verticalGravity = Alignment.CenterVertically
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
