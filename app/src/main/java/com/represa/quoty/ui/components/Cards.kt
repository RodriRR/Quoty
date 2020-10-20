package com.represa.quoty.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.Text
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.Surface
import androidx.compose.material.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.runtime.frames.Frame
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.WithConstraints
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.ui.tooling.preview.Preview
import com.represa.quoty.data.model.database.QuoteDatabase
import dev.chrisbanes.accompanist.coil.CoilImage
//import dev.chrisbanes.accompanist.coil.CoilImage
import java.time.format.TextStyle

@Composable
fun QuoteCard(quote: QuoteDatabase) {
    Surface() {

        WithConstraints() {

            Card(
                elevation = 3.dp,
                modifier = Modifier.height(350.dp).width(230.dp).padding(5.dp, 5.dp, 10.dp, 5.dp),
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
                }
            }
        }
    }
}