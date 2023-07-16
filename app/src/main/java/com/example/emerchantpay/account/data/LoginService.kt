package com.example.emerchantpay.account.data

import com.example.emerchantpay.account.domain.model.User
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers

interface LoginService {

    @Headers("Accept: application/json")
    @GET("user")
    suspend fun performLogin(@Header("Authorization") authHeader: String): Response<User>
}