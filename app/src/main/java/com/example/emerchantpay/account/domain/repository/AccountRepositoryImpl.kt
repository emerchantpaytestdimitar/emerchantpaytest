package com.example.emerchantpay.account.domain.repository

import com.example.emerchantpay.account.data.remote.ProfileService
import com.example.emerchantpay.account.data.remote.TokenService
import com.example.emerchantpay.account.domain.model.AccessTokenModel
import com.example.emerchantpay.account.domain.model.User
import com.example.emerchantpay.data.remote.CLIENT_ID
import com.example.emerchantpay.data.remote.CLIENT_SECRET
import com.example.emerchantpay.data.remote.REDIRECT_URL
import okhttp3.Credentials
import retrofit2.Response
import java.io.IOException

class AccountRepositoryImpl(
    private val service: TokenService,
    private val profileService: ProfileService
) : AccountRepository {

    override suspend fun getToken(code: String): String? {

        val response = service.getToken(CLIENT_ID, CLIENT_SECRET, code, REDIRECT_URL)

        if (response.isSuccessful) {

            val responseBody: AccessTokenModel? = response.body()

            return responseBody?.accessToken
        } else {
            val errorBody = response.errorBody()?.string()
            throw IOException("Unexpected response $response")
        }
    }

    override suspend fun performLogin(token: String): User? {
        val response = profileService.performLogin(Credentials.basic("", token))
        if (response.isSuccessful) {
            return response.body()
        } else {
            return null
        }
    }

    override suspend fun listFollowers(user: String, token: String): List<User> {
        val response = profileService.listFollowers(user = user, token = "Bearer $token")
        if (response.isSuccessful) {
            response.body()?.let {
                return it
            }
            return listOf()
        }
        return listOf()
    }

    override suspend fun listFollowing(user: String, token: String): List<User> {
        val response = profileService.listFollowing(user = user, token = "Bearer $token")
        if (response.isSuccessful) {
            response.body()?.let {
                return it
            }
            return listOf()
        }
        return listOf()
    }

    override suspend fun searchUsers(query: String, token: String): List<User> {
        return profileService.searchUsers(query = query, token = "Bearer $token").items
    }

    override suspend fun getUser(userId: String, token: String): User {
        val response = profileService.getUser(userId = userId, token = token)

        if (response.isSuccessful) {
            response.body()?.let {
                return it
            }
        }

        return User()
    }

}