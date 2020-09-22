package com.represa.quoty.util

import com.represa.quoty.data.Repository
import com.represa.quoty.data.database.QDatabase
import com.represa.quoty.ui.viewmodel.LoginViewModel
import com.represa.quoty.ui.viewmodel.MainViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    viewModel { LoginViewModel(get()) }
    viewModel { MainViewModel(get()) }

    single { QDatabase.getDatabase(androidContext()) }
    single { Repository(get()) }

}