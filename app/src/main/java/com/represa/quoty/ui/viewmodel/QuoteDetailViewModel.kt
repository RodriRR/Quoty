package com.represa.quoty.ui.viewmodel


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.represa.quoty.data.Repository
import com.represa.quoty.data.model.database.QuoteDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class QuoteDetailViewModel(private val repository: Repository) : ViewModel() {

    lateinit var quote : QuoteDatabase

    fun getQuote(id: Int){
        viewModelScope.launch(Dispatchers.IO) {
            quote = repository.getQuote(id)
        }
    }
}


