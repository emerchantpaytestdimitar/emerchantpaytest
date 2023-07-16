package com.example.emerchantpay.data.remote

import android.util.Log
import okhttp3.Interceptor
import okhttp3.Response

class LoginInterceptor: Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()

        Log.d("OkHttp", "Before sending request: ${request.url}")

        val response = chain.proceed(request)

        Log.d("OkHttp", "Received response for ${response.request.url}")

        return response
    }
}