package com.represa.quoty.ui.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.represa.quoty.data.Repository
import com.represa.quoty.util.QuoteConverter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MainViewModel(private val repository: Repository) : ViewModel() {

    var prueba by mutableStateOf(listOf<String>())
        private set

    private val flow = repository.prueba()

    fun getQuotes(){
        viewModelScope.launch(Dispatchers.Main){
            flow.collect{
                it.forEach { quote ->
                    prueba = prueba + listOf("")
                }
            }
        }
        viewModelScope.launch(Dispatchers.IO){
            repository.clear()
            var response = repository.fetchQuotes()
            response.forEach{ quote ->
                var databaseQuote = QuoteConverter.quoteNetworkToDatabase(quote)
                repository.insert(databaseQuote)
                delay(5000)
            }
        }
    }
}


