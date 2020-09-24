package com.represa.quoty.ui.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.represa.quoty.data.Repository
import com.represa.quoty.data.model.network.LoginStatus
import com.represa.quoty.util.QuoteConverter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MainViewModel(private val repository: Repository) : ViewModel() {

    var search by mutableStateOf("")


    var prueba by mutableStateOf(listOf<String>())
        private set

    val flow = repository.prueba()



    fun getQuotes(search : String){
        if(!search.isNullOrEmpty()) {
            viewModelScope.launch(Dispatchers.IO) {
                repository.clear()
                var response = repository.fetchQuotes(search).map { quote ->
                    QuoteConverter.quoteNetworkToDatabase(quote)
                }
                if (!response.isNullOrEmpty()) {
                    repository.insertListOfQuotes(response)
                }
            }
        }
    }
}


