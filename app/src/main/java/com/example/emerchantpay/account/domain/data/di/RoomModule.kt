package com.example.emerchantpay.data.di

import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val roomModule = module {

    single {
        AppDatabase.getInstance(androidContext())
    }
}