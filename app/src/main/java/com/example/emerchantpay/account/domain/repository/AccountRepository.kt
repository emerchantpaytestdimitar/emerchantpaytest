package com.example.emerchantpay.account.domain.repository

import com.example.emerchantpay.account.data.LoginService
import com.example.emerchantpay.account.data.TokenService
import com.example.emerchantpay.account.domain.model.AccessTokenModel
import com.example.emerchantpay.account.domain.model.User
import com.example.emerchantpay.data.remote.CLIENT_ID
import com.example.emerchantpay.data.remote.CLIENT_SECRET
import com.example.emerchantpay.data.remote.REDIRECT_URL
import okhttp3.Credentials
import java.io.IOException

class AccountRepository(private val service: TokenService, private val loginService: LoginService) {

    suspend fun getToken(code: String): String? {

        val response = service.getToken(CLIENT_ID, CLIENT_SECRET, code, REDIRECT_URL)

        if (response.isSuccessful) {

            val responseBody: AccessTokenModel? = response.body()

            return responseBody?.accessToken
        } else {
            val errorBody = response.errorBody()?.string()
            throw IOException("Unexpected response $response")
        }
    }

    suspend fun performLogin(token: String): User? {
        val response = loginService.performLogin(Credentials.basic("", token))
         if (response.isSuccessful) {
            return  response.body()
        } else {
            // Handle the error appropriately here, for example, you might want to throw an exception or log an error.
             return  null
        }
    }
}