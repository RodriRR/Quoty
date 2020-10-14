package com.represa.quoty.ui.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.represa.quoty.data.Repository
import com.represa.quoty.data.model.database.QuoteDatabase
import com.represa.quoty.data.model.network.images.Image
import com.represa.quoty.util.QuoteConverter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.ConflatedBroadcastChannel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class MainViewModel(private val repository: Repository) : ViewModel() {

    private val SEARCH_DELAY_MILLIS = 1000L

    var search by mutableStateOf("god")


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

    private fun getQuotes(search : String){
        if(!search.isNullOrEmpty()) {
            viewModelScope.launch(Dispatchers.IO) {
                try {
                    var response = repository.fetchQuotes(search) //.map { quote ->
                        //var image = getRandomImage()
                        //QuoteConverter.quoteNetworkToDatabase(quote, "image")
                    //}
                    var images = getRandomImages(response.size)
                    var quotes : MutableList<QuoteDatabase> = mutableListOf()
                    for(i in response.indices){
                        quotes.add(QuoteConverter.quoteNetworkToDatabase(response[i], images[i]))
                    }
                    repository.insertListOfQuotes(quotes)
                }catch(e : Exception){
                    println(e)
                }
            }
        }
    }

    private suspend fun getRandomImages(quantity : Int) : List<Image>{
        return repository.getRandomImages(search, quantity)
    }
}


