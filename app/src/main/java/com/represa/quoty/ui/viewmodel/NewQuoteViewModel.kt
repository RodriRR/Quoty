package com.represa.quoty.ui.viewmodel

import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.represa.quoty.data.Repository
import com.represa.quoty.data.model.network.images.Image
import com.represa.quoty.data.model.network.quote.NewQuote
import com.represa.quoty.data.model.network.quote.NewQuoteParams
import com.represa.quoty.util.QuoteConverter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Exception

class NewQuoteViewModel(private val repository: Repository) : ViewModel() {

    var author by mutableStateOf("")

    var body by mutableStateOf("")

    var topic by mutableStateOf("")

    fun createNewQuote(){
        var quote = NewQuote(NewQuoteParams(author = author, body = body))
        viewModelScope.launch(Dispatchers.IO) {
            try {
                var images = getRandomImage()
                var response = repository.createNewQuote(quote)
                var quoteDatabase = QuoteConverter.quoteNetworkToDatabase(response, images[0], false)
                repository.insert(quoteDatabase)
            }catch ( e : Exception){
                Log.e("CREATE NEW QUOTE ERROR", e.toString())
            }
        }
    }

    private suspend fun getRandomImage(): List<Image> {
        return repository.getRandomImages(topic, 1)
    }


}


