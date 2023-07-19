package com.example.emerchantpay.account.data.remote

import com.example.emerchantpay.account.domain.model.User
import com.example.emerchantpay.repository.domain.model.RepositoryModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.Path

interface ProfileService {

    @Headers("Accept: application/json")
    @GET("user")
    suspend fun performLogin(@Header("Authorization") authHeader: String): Response<User>

    @Headers("Accept: application/json")
    @GET("users/{user}/following")
    suspend fun listFollowing(
        @Path("user") user: String,
        @Header("Authorization") token: String
    ): List<User>

    @Headers("Accept: application/json")
    @GET("/users/{user}/followers")
    suspend fun listFollowers(
        @Path("user") user: String?,
        @Header("Authorization") token: String
    ): List<User>

    @Headers("Accept: application/json")
    @GET("users/{user}/starred")
    fun listStarredRepositories(@Path("user") user: String): List<RepositoryModel>
}