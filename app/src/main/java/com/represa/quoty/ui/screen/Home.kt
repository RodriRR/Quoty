package com.represa.quoty.ui.screen

import androidx.compose.foundation.Text
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumnFor
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.represa.quoty.data.model.database.QuoteDatabase
import com.represa.quoty.ui.components.MainCard
import com.represa.quoty.ui.viewmodel.MainViewModel

@Composable
fun Home(viewModel: MainViewModel) {

    var search = remember { mutableStateOf("") }

    var prueba = viewModel.flow.collectAsState(initial = emptyList())

    Scaffold(
        topBar = { TopBar(search, viewModel)} ){
            LazyColumnFor(
                items = prueba.value,
                itemContent = { MainCard(it) }
            )
        }

}

@Composable
fun TopBar(search : MutableState<String>, viewModel: MainViewModel){
    Column {
        TextField(value = search.value, onValueChange = { search.value = it }, label = { Text(text = "search" ) })
        Button(onClick = { viewModel.getQuotes(search.value)  }) {
            Text(text = "Search")
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
