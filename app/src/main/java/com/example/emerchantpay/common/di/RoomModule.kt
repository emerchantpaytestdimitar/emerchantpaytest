package com.example.emerchantpay.common.di

import com.example.emerchantpay.common.AppDatabase
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val roomModule = module {

    single {
        AppDatabase.getInstance(androidContext())
    }
}