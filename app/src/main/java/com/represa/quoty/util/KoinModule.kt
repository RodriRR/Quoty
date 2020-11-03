package com.represa.quoty.util

import com.represa.quoty.data.Repository
import com.represa.quoty.data.database.QDatabase
import com.represa.quoty.ui.viewmodel.LoginViewModel
import com.represa.quoty.ui.viewmodel.MainViewModel
import com.represa.quoty.ui.viewmodel.NewQuoteViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    single { QDatabase.getDatabase(androidContext()) }
    single { Repository(get()) }

    viewModel { LoginViewModel(get()) }
    viewModel { MainViewModel(get()) }
    viewModel { NewQuoteViewModel(get()) }

}