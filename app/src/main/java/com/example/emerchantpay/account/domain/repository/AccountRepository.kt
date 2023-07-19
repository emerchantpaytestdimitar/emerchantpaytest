package com.example.emerchantpay.account.domain.repository

import com.example.emerchantpay.account.domain.model.User

interface AccountRepository {

    suspend fun getToken(code: String): String?
    suspend fun performLogin(token: String): User?

    suspend fun listFollowers(user: String, token: String): List<User>

    suspend fun listFollowing(
        user: String,
        token: String
    ): List<User>

    suspend fun searchUsers(query: String, token: String): List<User>
}