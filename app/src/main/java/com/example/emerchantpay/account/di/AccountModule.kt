package com.example.emerchantpay.account.di

import com.example.emerchantpay.data.di.retrofitModule
import com.example.emerchantpay.data.di.roomModule
import org.koin.dsl.module

val accountModule = module {
    includes(roomModule, retrofitModule)
}