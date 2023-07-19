package com.example.emerchantpay.repository.data.remote

import androidx.annotation.Nullable
import com.example.emerchantpay.account.domain.model.User
import com.example.emerchantpay.repository.domain.model.RepositoryModel
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.PUT
import retrofit2.http.Path


interface RepositoryService {
    @Headers("Accept: application/json")
    @DELETE("user/starred/{owner}/{repo}")
    suspend fun unStarRepo(
        @Header("Authorization") token: String,
        @Path("owner") owner: String,
        @Path("repo") repo: String
    ): Response<Void>

    @Headers("Accept: application/json")
    @PUT("user/starred/{owner}/{repo}")
    suspend fun starRepo(
        @Header("Authorization") token: String,
        @Path("owner") owner: String,
        @Path("repo") repo: String
    ): Response<Void>

    @Headers("Accept: application/json")
    @GET("users/{user}/starred")
    suspend fun getRepositories(@Path("user") user: String): List<RepositoryModel>

    @Headers("Accept: application/json")
    @GET("repos/{owner}/{repo}")
    suspend fun getRepository(
        @Path("owner") owner: String,
        @Path("repo") repo: String
    ): RepositoryModel

    @Headers("Accept: application/json")
    @GET("/repos/{owner}/{repo}/contributors")
    suspend fun listRepoContributors(
        @Path("owner") owner: String?,
        @Path("repo") repo: String?
    ): List<User>?
}