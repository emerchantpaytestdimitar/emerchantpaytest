package com.example.emerchantpay.search.di

import com.example.emerchantpay.data.di.retrofitModule
import com.example.emerchantpay.data.di.roomModule
import org.koin.dsl.module

val searchModule = module {
    includes(roomModule, retrofitModule)
}