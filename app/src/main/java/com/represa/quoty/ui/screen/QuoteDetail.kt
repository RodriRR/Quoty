package com.represa.quoty.ui.screen

import androidx.compose.animation.ColorPropKey
import androidx.compose.animation.core.*
import androidx.compose.animation.transition
import androidx.compose.foundation.Icon
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawOpacity
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.ui.tooling.preview.Preview
import com.represa.quoty.ui.viewmodel.QuoteDetailViewModel
import dev.chrisbanes.accompanist.coil.CoilImage
import org.koin.androidx.compose.getViewModel

@Preview
@Composable
fun QuoteDetail(id: String?) {

    val viewModel = getViewModel<QuoteDetailViewModel>()
    viewModel.getQuote(id!!.toInt())

    var favButtonState : MutableState<FavButtonState> =  remember { viewModel.isFavourite }

    ConstraintLayout(Modifier.fillMaxSize().background(Color.Black)) {

        val (quoteCard, bottomCard, favButton, upbutton, downButton) = createRefs()

        Card(elevation = 3.dp,
            modifier = Modifier.height(350.dp).width(230.dp).padding(5.dp, 5.dp, 10.dp, 5.dp)
                .constrainAs(quoteCard) {
                    top.linkTo(parent.top, 50.dp)
                    start.linkTo(parent.start, 16.dp)
                    end.linkTo(parent.end, 16.dp)
                },
        backgroundColor = Color.Red) {

            Box(modifier = Modifier.fillMaxSize()) {
                CoilImage(
                    data = viewModel.image,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )
            }

        }

        val transitionFavButton = transition(
            definition = favButtonTransition(),
            initState = favButtonState.value,
            toState = if (favButtonState.value == FavButtonState.IDLE) {
                FavButtonState.PRESSED
            } else {
                FavButtonState.IDLE
            }
        )

        FavButton(
            buttonState = favButtonState,
            state = transitionFavButton,
            modifier = Modifier.constrainAs(favButton) {
                start.linkTo(parent.start)
                end.linkTo(parent.end)
                bottom.linkTo(parent.bottom, 100.dp)
            },
            viewModel = viewModel)
    }
}

enum class FavButtonState {
    INIT,
    IDLE,
    PRESSED
}

@Composable
fun FavButton(
    buttonState: MutableState<FavButtonState>,
    state: TransitionState,
    modifier: Modifier,
    viewModel : QuoteDetailViewModel
) {
    Box(
        modifier = modifier.clickable(
            onClick = {
               /* buttonState.value = */if (buttonState.value == FavButtonState.IDLE) {
                    viewModel.favQuote() //FavButtonState.PRESSED
                } else {
                     //FavButtonState.IDLE
                }
            })
    ) {
        ButtonContent(buttonState = buttonState, state = state)
    }
}

@Composable
fun ButtonContent(
    buttonState: MutableState<FavButtonState>,
    state: TransitionState
) {
    if (buttonState.value == FavButtonState.PRESSED) { //1
        Icon(
            tint = state[favButtonBackgroundColor],
            asset = Icons.Default.Favorite, //copy(defaultHeight = state[], defaultWidth = ),
            modifier = Modifier.drawOpacity(state[favButtonPressedOpacity])
        )
    } else {
        Icon(
            tint = Color.White,
            asset = Icons.Default.FavoriteBorder
        )
    }
}


private val favButtonPressedOpacity = FloatPropKey()
private val favButtonBackgroundColor = ColorPropKey()
private val favButtonSize = IntPropKey()

private fun favButtonTransition() = transitionDefinition<FavButtonState> {
    state(FavButtonState.IDLE) {
        this[favButtonPressedOpacity] = 1f
        this[favButtonBackgroundColor] = Color.Red
    }

    state(FavButtonState.PRESSED) {
        this[favButtonPressedOpacity] = 0f
        this[favButtonBackgroundColor] = Color.White
    }

    transition(
        fromState = FavButtonState.IDLE,
        toState = FavButtonState.PRESSED
    ) {
        favButtonPressedOpacity using tween(durationMillis = 3000, delayMillis = 100)
        favButtonBackgroundColor using tween(durationMillis = 3000, delayMillis = 100)
    }
    transition(
        fromState = FavButtonState.PRESSED,
        toState = FavButtonState.IDLE
    ) {
        favButtonPressedOpacity using tween(durationMillis = 3000, delayMillis = 100)
        favButtonBackgroundColor using tween(durationMillis = 3000, delayMillis = 100)
    }
}