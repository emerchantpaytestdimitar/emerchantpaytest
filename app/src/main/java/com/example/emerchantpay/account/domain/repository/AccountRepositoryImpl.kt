package com.example.emerchantpay.account.domain.repository

import android.util.Log
import com.example.emerchantpay.account.data.remote.ProfileService
import com.example.emerchantpay.account.data.remote.TokenService
import com.example.emerchantpay.account.domain.model.AccessTokenModel
import com.example.emerchantpay.account.domain.model.ConverterUserUtil
import com.example.emerchantpay.account.domain.model.User
import com.example.emerchantpay.common.AppDatabase
import com.example.emerchantpay.data.remote.CLIENT_ID
import com.example.emerchantpay.data.remote.CLIENT_SECRET
import com.example.emerchantpay.data.remote.REDIRECT_URL
import com.example.emerchantpay.repository.domain.model.ConverterRepositoryUtil
import com.example.emerchantpay.repository.domain.model.RepositoryModel
import okhttp3.Credentials
import retrofit2.Response
import java.io.IOException

class AccountRepositoryImpl(
    private val service: TokenService,
    private val profileService: ProfileService,
    private val db: AppDatabase
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
        return if (response.isSuccessful) {
            response.body()
        } else {
            null
        }
    }

    override suspend fun listFollowers(user: User, token: String): List<User> {

        val response: Response<List<User>>
        var isSuccessful = false
        var body: List<User> = listOf()
        try {
            response = profileService.listFollowers(user = user.login, token = "Bearer $token")
            isSuccessful = response.isSuccessful
            response.body()?.let {
                body = it
            }
        } catch (e: Exception) {
            isSuccessful = false
            Log.e(e.message, e.toString())
        }

        return handleUsers(user = user, body = body, isSuccessful = isSuccessful)
    }

    override suspend fun listFollowing(user: User, token: String): List<User> {
        val response: Response<List<User>>
        var isSuccessful = false
        var body: List<User> = listOf()
        try {
            response = profileService.listFollowing(user = user.login, token = "Bearer $token")
            isSuccessful = response.isSuccessful
            response.body()?.let {
                body = it
            }
        } catch (e: Exception) {
            isSuccessful = false
            Log.e(e.message, e.toString())
        }

        return handleUsers(user = user, body = body, isSuccessful = isSuccessful)
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

    private suspend fun handleUsers(
        user: User,
        body: List<User>,
        isSuccessful: Boolean
    ): List<User> {
        val ownerId = user.id
        if (isSuccessful) {
            db.repositoryModelDao().deleteAllReposByStarredByUserId(ownerId)
            body.let { users ->
                val userDbList = ConverterUserUtil.convertUserListToUserDbList(users)
                userDbList.forEach {
                    it.followedByUserId = user.id
                    db.usesrDao().insertRepository(it)
                }
            }
        }

        return ConverterUserUtil.convertUserDbListToUserList(
            db.usesrDao().getRepositoriesAndOwnerByOwnerId(user.id)
        )
    }

}