package com.represa.quoty.ui.screen

import androidx.compose.animation.core.FloatPropKey
import androidx.compose.animation.core.transitionDefinition
import androidx.compose.animation.core.tween
import androidx.compose.animation.transition
import androidx.compose.foundation.Text
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRowFor
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.WithConstraints
import androidx.compose.ui.draw.drawOpacity
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.ui.tooling.preview.Preview
import com.represa.quoty.ui.components.QuoteCard
import com.represa.quoty.ui.components.QuoteCardDummy
import com.represa.quoty.ui.viewmodel.MainViewModel
import com.represa.quoty.util.ui.AbsoluteAlignment


@Preview
@Composable
fun newHome() {
    Surface(Modifier.fillMaxSize()) {
        WithConstraints() {
            Box(Modifier.fillMaxSize()) {

                var searchMode by remember { mutableStateOf<Boolean>(false) }

                var visibility by remember { mutableStateOf<Boolean>(false) }

                val transition = transition(
                    definition = remember { searchTransition() },
                    initState = SearchComponentState.SearchDisabled,
                    toState = if (searchMode) {
                        SearchComponentState.SearchEnabled
                    } else {
                        SearchComponentState.SearchDisabled
                    },
                    onStateChangeFinished = { state ->
                        when(state){
                            SearchComponentState.SearchEnabled -> visibility = true
                        }
                    }

                )

                val verticalBias = transition[searchComponentVerticalBias]

                Column(
                    modifier = Modifier.align(AbsoluteAlignment(verticalBias)).background(Color.Red)
                ) {
                    Text(
                        modifier = Modifier
                            .clickable(onClick = { }),
                        text = "Find Your \nQuote",
                        fontSize = 28.sp,
                        lineHeight = 28.sp
                    )

                    var textField by remember { mutableStateOf("") }

                    Row {

                        TextField(
                            value = textField,
                            onTextInputStarted = { searchMode = !searchMode },
                            onValueChange = { textField = it },
                            label = { Text(text = "search") },
                            imeAction = ImeAction.Done,
                            onImeActionPerformed = { action, softwareController ->
                                if(action == ImeAction.Done){
                                    softwareController!!.hideSoftwareKeyboard()

                                }
                            }
                        )

                        Button(onClick = { searchMode = false; textField = ""; visibility = false}) {
                        }
                    }
                }
                Box(modifier = Modifier.drawOpacity(
                    1f//if(visibility){ 1f }else{ 0f }
                ).wrapContentHeight().fillMaxWidth().background(Color.Black).align(Alignment.CenterStart)){
                    LazyRowFor(items = mutableListOf(0..2), itemContent = { QuoteCardDummy() })
                }
            }
        }
    }
}

enum class SearchComponentState {
    SearchEnabled,
    SearchDisabled
}

private val searchComponentVerticalBias = FloatPropKey()

private fun searchTransition() = transitionDefinition<SearchComponentState> {
    //Properties when the search is Disabled
    state(SearchComponentState.SearchDisabled) {
        this[searchComponentVerticalBias] = 0f
    }
    state(SearchComponentState.SearchEnabled) {
        this[searchComponentVerticalBias] = -1f
    }
    transition(
        fromState = SearchComponentState.SearchDisabled,
        toState = SearchComponentState.SearchEnabled
    ) {
        searchComponentVerticalBias using tween(durationMillis = 500, delayMillis = 250)
    }
    transition(
        fromState = SearchComponentState.SearchEnabled,
        toState = SearchComponentState.SearchDisabled
    ) {
        searchComponentVerticalBias using tween(durationMillis = 500, delayMillis = 250)
    }
}


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
    ConstraintLayout(
        Modifier.padding(0.dp, 16.dp, 0.dp, 0.dp).fillMaxSize()
    ) {

        var searchMode by remember { mutableStateOf(false) }

        // Create references for the composables to constrain
        val (title, subtitle, searchBar, lazyColumn) = createRefs()

        /*val modifierSearch = Modifier.padding(16.dp, 16.dp, 0.dp, 16.dp)
            .clickable(onClick = { searchMode = false })
            .constrainAs(title) {
                top.linkTo(parent.top)
                absoluteLeft.linkTo(parent.absoluteLeft)
            }.background(Color.Red)

        val modifierNoSearch = Modifier.padding(16.dp, 16.dp, 0.dp, 16.dp)
            .clickable(onClick = { searchMode = true })
            .constrainAs(title) {
                top.linkTo(parent.top)
                absoluteLeft.linkTo(parent.absoluteLeft)
                bottom.linkTo(parent.bottom)
                absoluteRight.linkTo(parent.absoluteRight)
            }.background(Color.Cyan)*/

        /*val searchBarConstrains =
            Modifier.padding(16.dp, 0.dp, 16.dp, 16.dp).constrainAs(searchBar) {
                top.linkTo(title.bottom)
            }*/


        val paddingSearch = PaddingValues(16.dp, 16.dp, 0.dp, 16.dp)
        val paddingNoSearch = PaddingValues(16.dp, 200.dp, 0.dp, 16.dp)

        Text(
            modifier = Modifier.padding(
                if (searchMode) {
                    paddingSearch
                } else {
                    paddingNoSearch
                }
            )
                .clickable(onClick = { searchMode = !searchMode })
                .constrainAs(title) {
                    top.linkTo(parent.top)
                    absoluteLeft.linkTo(parent.absoluteLeft)
                },
            text = "Find Your \nQuote",
            fontSize = 28.sp,
            lineHeight = 28.sp
        )

        /*TopBar(
            viewModel = viewModel,
            modifier = searchBarConstrains,
            onValueChange = { searchMode = true })

        Text(
            modifier = Modifier.padding(16.dp, 0.dp, 0.dp, 16.dp).constrainAs(subtitle) {
                top.linkTo(searchBar.bottom)
            },
            text = "Results",
            fontSize = 20.sp
        )

        LazyRowFor(
            items = prueba.value,
            itemContent = { QuoteCard(it) },
            modifier = Modifier.padding(5.dp, 0.dp, 5.dp, 0.dp).constrainAs(lazyColumn) {
                top.linkTo(subtitle.bottom)
                bottom.linkTo(parent.bottom)
                absoluteLeft.linkTo(parent.absoluteLeft)
                absoluteRight.linkTo(parent.absoluteRight)
            }
        )*/
    }

}


@Composable
fun TopBar(viewModel: MainViewModel, modifier: Modifier, onValueChange: () -> Unit) {
    Column(modifier = modifier) {
        TextField(
            value = viewModel.search,
            onValueChange = { it ->
                viewModel.setSearchQuery(it); viewModel.search = it; onValueChange
            },
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
