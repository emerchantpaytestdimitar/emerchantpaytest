package com.example.emerchantpay.repository.data.remote

import com.example.emerchantpay.repository.domain.RepositoriesResponse
import com.example.emerchantpay.repository.domain.RepositoryModel
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path

interface RepositoryService {
    @Headers("Accept: application/json")
    @GET("users/{user}/repos")
    suspend fun getRepositories(@Path("user") user: String): List<RepositoryModel>
}