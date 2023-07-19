package com.example.emerchantpay.account.domain.repository

import com.example.emerchantpay.account.domain.model.User
import retrofit2.http.Header
import retrofit2.http.Path

interface AccountRepository {

    suspend fun getToken(code: String): String?
    suspend fun performLogin(token: String): User?

    suspend fun listFollowers(user: String, token: String): List<User>

    suspend fun listFollowing(
        user: String,
        token: String
    ): List<User>

}