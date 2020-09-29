package com.represa.quoty.ui.viewmodel

import android.widget.Toast
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.viewinterop.viewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.represa.quoty.data.Repository
import com.represa.quoty.data.model.database.QuoteDatabase
import com.represa.quoty.data.model.network.LoginStatus
import com.represa.quoty.util.QuoteConverter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.ConflatedBroadcastChannel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class MainViewModel(private val repository: Repository) : ViewModel() {

    private val SEARCH_DELAY_MILLIS = 1000L

    var search by mutableStateOf("")


    var prueba by mutableStateOf(listOf<String>())
        private set

    private val searchChanel = ConflatedBroadcastChannel<String>()

    val flow = searchChanel.asFlow().debounce(SEARCH_DELAY_MILLIS).flatMapLatest { search ->
        //if (search.isNotEmpty()) {
            getQuotes(search)
            repository.prueba(search)
        //}else{
          //  var r = emptyList<QuoteDatabase>()
            //r.asFlow()
        //}
    }.flowOn(Dispatchers.IO)

    init {
        clearDatabase()
    }

    fun setSearchQuery(search: String) {
        //We use .offer() to send the element to all the subscribers.
        searchChanel.offer(search)
    }


    private fun clearDatabase(){
        viewModelScope.launch(Dispatchers.IO){
            repository.clear()
        }
    }

    fun getQuotes(search : String){
        if(!search.isNullOrEmpty()) {
            viewModelScope.launch(Dispatchers.IO) {
                try {
                    var response = repository.fetchQuotes(search).map { quote ->
                        QuoteConverter.quoteNetworkToDatabase(quote)
                    }
                    repository.insertListOfQuotes(response)
                }catch(e : Exception){

                }
            }
        }
    }
}


