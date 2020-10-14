package com.represa.quoty.ui.screen

import androidx.compose.foundation.Text
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumnFor
import androidx.compose.foundation.lazy.LazyRowFor
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.font.font
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.represa.quoty.R
import com.represa.quoty.data.model.database.QuoteDatabase
import com.represa.quoty.ui.components.MainCard
import com.represa.quoty.ui.components.QuoteCard
import com.represa.quoty.ui.viewmodel.MainViewModel

@Composable
fun Home(viewModel: MainViewModel) {

    var prueba = viewModel.flow.collectAsState(initial = emptyList())

    //Scaffold(
    //topBar = { TopBar(search, viewModel) }) {

    /*LazyColumnFor(
        items = prueba.value,
        itemContent = { MainCard(it) }
    )
}*/
    //}
    ConstraintLayout(Modifier.padding(0.dp,16.dp,0.dp,0.dp)) {
        // Create references for the composables to constrain
        val (title, subtitle, searchBar, lazyColumn) = createRefs()

        val searchBarConstrains = Modifier.padding(16.dp,0.dp,16.dp,16.dp).constrainAs(searchBar){
            top.linkTo(title.bottom)
        }

        Text(
            modifier = Modifier.padding(16.dp,16.dp,0.dp,16.dp).constrainAs(title){top.linkTo(parent.top)},
            text = "Find Your \nQuote",
            fontSize = 28.sp,
            lineHeight = 28.sp
        )

        TopBar(viewModel = viewModel , modifier = searchBarConstrains)

        Text(
            modifier = Modifier.padding(16.dp,0.dp,0.dp,16.dp).constrainAs(subtitle){
                top.linkTo(searchBar.bottom)},
            text = "Results",
            fontSize = 20.sp
        )

        LazyRowFor(
            items = prueba.value,
            itemContent = { QuoteCard(it) },
            modifier = Modifier.padding(5.dp,0.dp,5.dp,0.dp).constrainAs(lazyColumn){
                top.linkTo(subtitle.bottom)
                bottom.linkTo(parent.bottom)
                absoluteLeft.linkTo(parent.absoluteLeft)
                absoluteRight.linkTo(parent.absoluteRight)
            }
        )
    }

}


@Composable
fun TopBar(viewModel: MainViewModel, modifier: Modifier) {
    Column(modifier = modifier) {
        TextField(
            value = viewModel.search,
            onValueChange = { it -> viewModel.setSearchQuery(it); viewModel.search = it },
            label = { Text(text = "search") })
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
