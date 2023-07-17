package com.example.emerchantpay.search.di

import com.example.emerchantpay.common.di.retrofitModule
import com.example.emerchantpay.common.di.roomModule
import org.koin.dsl.module

val searchModule = module {
    includes(roomModule, retrofitModule)
}