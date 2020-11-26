package com.represa.quoty.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.ui.tooling.preview.Preview
import com.represa.quoty.data.model.network.quote.NewQuoteParams
import com.represa.quoty.ui.viewmodel.NewQuoteViewModel
import org.koin.androidx.compose.getViewModel

@Preview
@Composable
fun NewQuote(viewModel: NewQuoteViewModel = getViewModel()){

    Box(modifier = Modifier.fillMaxSize().background(Color.White)){

        Column(modifier = Modifier.wrapContentSize().align(Alignment.Center)) {
            TextField( label = { Text(text = "author")}, value = viewModel.author, onValueChange = { viewModel.author = it})

            TextField( label = { Text(text = "body")}, value = viewModel.body , onValueChange = { viewModel.body = it} )

            TextField( label = { Text(text = "topic")}, value = viewModel.topic , onValueChange = { viewModel.topic = it} )


            Button(onClick = {viewModel.createNewQuote()}) {
                Text(text = "create")
            }
        }
    }

}