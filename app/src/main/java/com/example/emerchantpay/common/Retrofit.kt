package com.example.emerchantpay.data.remote

import com.example.emerchantpay.account.data.remote.LoginInterceptor
import com.example.emerchantpay.account.data.remote.TokenInterceptor
import com.example.emerchantpay.common.di.BASE_API_URL
import com.example.emerchantpay.common.di.BASE_URL
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

const val CLIENT_ID = "deef52e906630257fc60"
const val CLIENT_SECRET = "fbbad320152a8aaf2b3a05491d251bd011daf0c4"
const val REDIRECT_URL = "https://www.emerchantpay.com/"

class Retrofit {

    companion object {
        @Volatile
        private var INSTANCE: Retrofit? = null
        private var INSTANCE_API: Retrofit? = null

        fun getInstance(): Retrofit {

            val okHttpClient = OkHttpClient.Builder()
                .addInterceptor(TokenInterceptor())
                .build()

            return INSTANCE ?: synchronized(this) {
                val instance = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(okHttpClient)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                INSTANCE = instance
                instance
            }
        }

        fun getInstanceApi(): Retrofit {

            val okHttpClient = OkHttpClient.Builder()
                .addInterceptor(LoginInterceptor())
                .build()

            return INSTANCE_API ?: synchronized(this) {
                val instance = Retrofit.Builder()
                    .baseUrl(BASE_API_URL)
                    .client(okHttpClient)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                INSTANCE_API = instance
                instance
            }
        }
    }
}