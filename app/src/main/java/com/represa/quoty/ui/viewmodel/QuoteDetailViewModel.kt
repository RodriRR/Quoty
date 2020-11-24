package com.represa.quoty.ui.viewmodel


import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.represa.quoty.data.Repository
import com.represa.quoty.data.model.database.QuoteDatabase
import com.represa.quoty.data.model.network.quote.QuoteNetwork
import com.represa.quoty.ui.screen.FavButtonState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class QuoteDetailViewModel(private val repository: Repository) : ViewModel() {

    lateinit var quote : QuoteDatabase
    lateinit var favouriteQuotes : List<QuoteDatabase>
    var image by mutableStateOf("")
        private set

    var prueba : MutableState<FavButtonState> = mutableStateOf(FavButtonState.IDLE)
        private set

    init {
        getFavouriteQuotes()
    }

    private fun getFavouriteQuotes(){
        viewModelScope.launch(Dispatchers.IO) {
            favouriteQuotes = repository.getFavouritesQuote()
            withContext(Dispatchers.Main){
                if(favouriteQuotes.contains(quote)){
                    prueba.value = FavButtonState.PRESSED
                }
            }
        }
    }

    fun getQuote(id: Int){
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                quote = repository.getQuote(id)
            }
            image = quote.image
        }
    }
}


