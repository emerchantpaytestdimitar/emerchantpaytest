package com.example.emerchantpay.application

import android.app.Application
import com.example.emerchantpay.data.di.retrofitModule
import com.example.emerchantpay.data.di.roomModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class EmerchantpayApplication: Application() {

    override fun onCreate() {
        super.onCreate()

        loadKoinModules()
    }

    private fun loadKoinModules() {
        startKoin {
            androidContext(this@EmerchantpayApplication)
            modules(retrofitModule, roomModule, retrofitModule, roomModule)
        }
    }
}