package com.represa.quoty.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.*
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.navigate
import com.represa.quoty.data.model.database.QuoteDatabase
import com.represa.quoty.ui.viewmodel.MainViewModel
import dev.chrisbanes.accompanist.coil.CoilImage
import org.koin.androidx.compose.getViewModel

@Composable
fun QuoteCard(
    navController: NavHostController,
    quote: QuoteDatabase,
    viewModel: MainViewModel = getViewModel()
) {
    Surface() {

        if (quote.body.length > 100) {
            quote.body = quote.body.substring(0, 100) + "..."
        }

        Card(
            elevation = 3.dp,
            modifier = Modifier.height(350.dp).width(230.dp).padding(5.dp, 5.dp, 10.dp, 5.dp)
                .clickable(
                    onClick = { navController.navigate("quoteDetail/" + quote.id) }
                ),
        ) {

            Box(modifier = Modifier.fillMaxSize()) {

                CoilImage(
                    data = quote.image,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )

                Box(modifier = Modifier.fillMaxSize().background(color = Color(0x4D000000)))

                Text(
                    text = quote.body,
                    color = Color.White,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(10.dp).align(Alignment.Center)
                )

                Text(
                    text = "- " + quote.author + " -",
                    modifier = Modifier.align(Alignment.BottomCenter)
                        .padding(0.dp, 0.dp, 0.dp, 10.dp),
                    color = Color.White
                )
            }
        }
    }
}