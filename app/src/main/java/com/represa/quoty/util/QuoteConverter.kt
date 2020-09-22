package com.represa.quoty.util

import com.represa.quoty.data.model.network.QuoteNetwork
import com.represa.quoty.data.model.database.QuoteDatabase

object QuoteConverter{

    fun quoteNetworkToDatabase(quoteNetwork: QuoteNetwork) : QuoteDatabase{
        return QuoteDatabase(quoteNetwork.id,
            quoteNetwork.dialogue,
            quoteNetwork.dialogue,
            quoteNetwork.tags.toString(),
            quoteNetwork.url,
            quoteNetwork.author_permalink,
            quoteNetwork.favorites_count,
            quoteNetwork.upvotes_count,
            quoteNetwork.downvotes_count,
            quoteNetwork.author,
            quoteNetwork.body)
    }
}