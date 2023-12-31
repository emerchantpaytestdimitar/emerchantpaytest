package com.example.emerchantpay.account.domain.repository

import com.example.emerchantpay.account.domain.model.User
import retrofit2.Response
import retrofit2.http.Path

interface AccountRepository {

    suspend fun getToken(code: String): String?
    suspend fun performLogin(token: String): User?

    suspend fun listFollowers(user: User, token: String): List<User>

    suspend fun listFollowing(
        user: User,
        token: String
    ): List<User>

    suspend fun searchUsers(query: String, token: String): List<User>

    suspend fun getUser(user: User, token: String): User
}