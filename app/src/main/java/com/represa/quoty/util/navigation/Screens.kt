package com.represa.quoty.util.navigation

import android.os.Bundle
import androidx.core.os.bundleOf

sealed class Screen(val title: String) {

    object ProfileDetails : Screen("PhraseDetail")
    object Profile : Screen("Phrases")
    object NewQuoteDetail : Screen("New Quote Details")
    object NewQuote : Screen("New Quote")
    object Search : Screen("Search")

    /** hack to generate the same Destination ID that the Compose Navigation lib generates **/
    val id: Int
        get() {
            return hashCode() + 0x00010000
        }

    fun saveState(): Bundle {
        return bundleOf(KEY_TITLE to title)
    }

    companion object {
        fun restoreState(bundle: Bundle): Screen {
            val title = bundle.getString(KEY_TITLE, Search.title)
            return when (title) {
                NewQuote.title -> NewQuote
                NewQuoteDetail.title -> NewQuoteDetail
                Profile.title -> Profile
                ProfileDetails.title -> ProfileDetails
                else -> Search
            }
        }

        const val KEY_TITLE = "title"
    }
}

internal val phrases = listOf(
    "Easy As Pie",
    "Wouldn't Harm a Fly",
    "No-Brainer",
    "Keep On Truckin'",
    "An Arm and a Leg",
    "Down To Earth",
    "Under the Weather",
    "Up In Arms",
    "Cup Of Joe",
    "Not the Sharpest Tool in the Shed",
    "Ring Any Bells?",
    "Son of a Gun",
    "Hard Pill to Swallow",
    "Close But No Cigar",
    "Beating a Dead Horse",
    "If You Can't Stand the Heat, Get Out of the Kitchen",
    "Cut To The Chase",
    "Heads Up",
    "Goody Two-Shoes",
    "Fish Out Of Water",
    "Cry Over Spilt Milk",
    "Elephant in the Room",
    "There's No I in Team",
    "Poke Fun At",
    "Talk the Talk",
    "Know the Ropes",
    "Fool's Gold",
    "It's Not Brain Surgery",
    "Fight Fire With Fire",
    "Go For Broke"
)