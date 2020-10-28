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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.represa.quoty.ui.components.QuoteCard
import com.represa.quoty.ui.viewmodel.MainViewModel
import com.represa.quoty.util.ui.AbsoluteAlignment
import org.koin.androidx.compose.getViewModel


@Composable
fun SearchView(viewModel: MainViewModel = getViewModel()) {
    Surface(Modifier.fillMaxSize()) {

        WithConstraints() {

            Box(Modifier.fillMaxSize()) {

                //Quotes to be shown
                var quotesSearched = viewModel.flow.collectAsState(initial = emptyList())

                //To make transitions possible, we need a var to determinete in which
                //state we are, if searchmode = true -> searching
                var searchMode by remember { mutableStateOf<Boolean>(false) }

                val transitionQuotesAlpha = transition(
                    definition = remember { quotesTransition() },
                    initState = QuotesState.QuotesHidden,
                    toState = if (!quotesSearched.value.isNullOrEmpty()) {
                        QuotesState.QuotesShown
                    } else {
                        QuotesState.QuotesHidden
                    },
                )

                val quotesAlpha = transitionQuotesAlpha[quotesAlpha]

                //This var manage the visibility of the quote searched
                var visibility by remember { mutableStateOf(false) }

                //This Box will show the quotes searched
                Box(
                    modifier = Modifier.drawOpacity(quotesAlpha).wrapContentHeight()
                        .fillMaxWidth().align(Alignment.CenterStart)
                ) {
                    LazyRowFor(
                        items = quotesSearched.value,
                        itemContent = { QuoteCard(it) },
                    )
                }

                //If we are in the state SearchDisable and then we click on the searchField
                //We are going to transit to the SearchEnable state
                val transitionSearchColumn = transition(
                    definition = remember { searchTransition() },
                    initState = SearchComponentState.SearchDisabled,
                    toState = if (!quotesSearched.value.isNullOrEmpty()) {
                        SearchComponentState.SearchEnabled
                    } else {
                        SearchComponentState.SearchDisabled
                    },
                    //When finish transition show the quotes view
                    onStateChangeFinished = { state ->
                        when (state) {
                            //SearchComponentState.SearchEnabled -> visibility = true
                        }
                    }
                )

                //If we are in the state SearchDisable and then we click on the searchField
                //We are going to transit to the SearchEnable state
                val transitionTitle = transition(
                    definition = remember { titleTransition() },
                    initState = TitleState.QuotesHidden,
                    toState = if (!quotesSearched.value.isNullOrEmpty()) {
                        TitleState.QuotesShown
                    } else {
                        TitleState.QuotesHidden
                    }
                )

                val verticalBias = transitionSearchColumn[searchComponentVerticalBias]
                val titleAlpha = transitionTitle[titleAlpha]

                Column(
                    modifier = Modifier.align(AbsoluteAlignment(verticalBias))
                ) {
                    Text(
                        modifier = Modifier.height((titleAlpha*100).dp).drawOpacity(titleAlpha)
                            .clickable(onClick = { }),
                        text = "Find Your \nQuote",
                        fontSize = 33.sp,
                        lineHeight = 33.sp
                    )

                    var textField by remember { mutableStateOf("") }

                    Row(Modifier.padding(0.dp, 10.dp, 0.dp, 0.dp)) {

                        TextField(
                            modifier = Modifier.background(Color.Red),
                            value = viewModel.search,
                            //onTextInputStarted = { searchMode = true },
                            onValueChange = { viewModel.setSearchQuery(it); viewModel.search = it },
                            label = { Text(text = "search") },
                            imeAction = ImeAction.Done,
                            onImeActionPerformed = { action, softwareController ->
                                if (action == ImeAction.Done) {
                                    softwareController!!.hideSoftwareKeyboard()
                                }
                            }
                        )

                        //Hidde quotes and transitate to SearchDisable
                        Button(onClick = {
                            viewModel.clearQuotesFlow(); textField = "clear"; visibility = false
                        }) {
                            Text(text = "clear")
                        }
                    }
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
        this[searchComponentVerticalBias] = -0.3f
    }
    state(SearchComponentState.SearchEnabled) {
        this[searchComponentVerticalBias] = -1f
    }
    transition(
        fromState = SearchComponentState.SearchDisabled,
        toState = SearchComponentState.SearchEnabled
    ) {
        searchComponentVerticalBias using tween(durationMillis = 400, delayMillis = 250)
    }
    transition(
        fromState = SearchComponentState.SearchEnabled,
        toState = SearchComponentState.SearchDisabled
    ) {
        searchComponentVerticalBias using tween(durationMillis = 500, delayMillis = 250)
    }
}

enum class QuotesState {
    QuotesShown,
    QuotesHidden
}

private val quotesAlpha = FloatPropKey()

private fun quotesTransition() = transitionDefinition<QuotesState> {
    //Properties when the search is Disabled
    state(QuotesState.QuotesHidden) {
        this[quotesAlpha] = 0f
    }
    state(QuotesState.QuotesShown) {
        this[quotesAlpha] = 1f
    }
    transition(
        fromState = QuotesState.QuotesHidden,
        toState = QuotesState.QuotesShown
    ) {
        quotesAlpha using tween(durationMillis = 500, delayMillis = 250)
    }
    transition(
        fromState = QuotesState.QuotesShown,
        toState = QuotesState.QuotesHidden
    ) {
        //This is not used because lazycolum erase the hole row
        quotesAlpha using tween(durationMillis = 0, delayMillis = 0)
    }
}


enum class TitleState {
    QuotesShown,
    QuotesHidden
}

private val titleAlpha = FloatPropKey()

private fun titleTransition() = transitionDefinition<TitleState> {
    //Properties when the search is Disabled
    state(TitleState.QuotesHidden) {
        this[titleAlpha] = 1f
    }
    state(TitleState.QuotesShown) {
        this[titleAlpha] = 0f
    }
    transition(
        fromState = TitleState.QuotesHidden,
        toState = TitleState.QuotesShown
    ) {
        titleAlpha using tween(durationMillis = 500, delayMillis = 250)
    }
    transition(
        fromState = TitleState.QuotesShown,
        toState = TitleState.QuotesHidden
    ) {
        //This is not used because lazycolum erase the hole row
        titleAlpha using tween(durationMillis = 500, delayMillis = 250)
    }
}
