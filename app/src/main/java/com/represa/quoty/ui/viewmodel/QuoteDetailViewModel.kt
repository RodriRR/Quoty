package com.represa.quoty.ui.viewmodel


import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.represa.quoty.data.Repository
import com.represa.quoty.data.model.database.QuoteDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class QuoteDetailViewModel(private val repository: Repository) : ViewModel() {

    lateinit var quote : QuoteDatabase
    var image by mutableStateOf("")
        private set


    fun getQuote(id: Int){
        viewModelScope.launch {
            quote = repository.getQuote(id)
            image = quote.image
        }
    }
}


