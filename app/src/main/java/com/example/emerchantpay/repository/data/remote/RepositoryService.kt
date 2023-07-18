package com.example.emerchantpay.repository.data.remote

import com.example.emerchantpay.account.domain.model.User
import com.example.emerchantpay.repository.domain.model.RepositoryModel
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path


interface RepositoryService {
    @Headers("Accept: application/json")
    @GET("users/{user}/repos")
    suspend fun getRepositories(@Path("user") user: String): List<RepositoryModel>

    @Headers("Accept: application/json")
    @GET("repos/{owner}/{repo}")
    suspend fun getRepository(
        @Path("owner") owner: String,
        @Path("repo") repo: String
    ): RepositoryModel

    @Headers("Accept: application/json")
    @GET("/users/{user}/followers")
    suspend fun listFollowers(@Path("user") user: String?): List<User>

    @Headers("Accept: application/json")
    @GET("/repos/{owner}/{repo}/contributors")
    suspend fun listRepoContributors(
        @Path("owner") owner: String?,
        @Path("repo") repo: String?
    ): List<User>?
}