package com.represa.quoty.util

import com.represa.quoty.data.model.network.quote.QuoteNetwork
import com.represa.quoty.data.model.database.QuoteDatabase
import com.represa.quoty.data.model.network.images.Image

object QuoteConverter{

    fun quoteNetworkToDatabase(quoteNetwork: QuoteNetwork, image : Image) : QuoteDatabase{
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
            quoteNetwork.body,
            image.urls.regular)
    }
}