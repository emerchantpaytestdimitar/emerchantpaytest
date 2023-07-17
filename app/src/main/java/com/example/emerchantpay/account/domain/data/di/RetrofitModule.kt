package com.example.emerchantpay.data.di

import com.example.emerchantpay.data.remote.Retrofit
import org.koin.core.qualifier.named
import org.koin.dsl.module

const val BASE_URL = "https://github.com"
const val BASE_API_URL = "https://api.github.com"
const val AUTHENTICATION_URL = "https://github.com/login/oauth/authorize"
const val REDIRECT_URL = "https://www.emerchantpay.com/"

val retrofitModule = module {
    single(named("token")) {
        Retrofit.getInstance()
    }

    single(named("login")){
        Retrofit.getInstanceApi()
    }
}