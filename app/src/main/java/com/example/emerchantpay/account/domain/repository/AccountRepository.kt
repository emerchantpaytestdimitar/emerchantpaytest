package com.example.emerchantpay.account.domain.repository

import com.example.emerchantpay.account.domain.model.User

interface AccountRepository {

    suspend fun getToken(code: String): String?
    suspend fun performLogin(token: String): User?

}