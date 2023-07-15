package com.example.emerchantpay.repository.di

import com.example.emerchantpay.data.di.retrofitModule
import com.example.emerchantpay.data.di.roomModule
import org.koin.dsl.module

val repositoryModule = module {
    includes(roomModule, retrofitModule)
}