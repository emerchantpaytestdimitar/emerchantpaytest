package com.example.emerchantpay.application

import android.app.Application
import com.example.emerchantpay.account.di.accountModule
import com.example.emerchantpay.data.di.retrofitModule
import com.example.emerchantpay.data.di.roomModule
import com.example.emerchantpay.repository.di.repositoryModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class EmerchantpayApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        loadKoinModules()
    }

    private fun loadKoinModules() {
        startKoin {
            androidContext(this@EmerchantpayApplication)
            modules(accountModule, retrofitModule, roomModule, repositoryModule, roomModule)
        }
    }
}