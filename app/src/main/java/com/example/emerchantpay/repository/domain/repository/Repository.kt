package com.example.emerchantpay.repository.domain.repository

import com.example.emerchantpay.repository.domain.model.RepositoryModel
import retrofit2.http.Path

interface Repository {

    suspend fun getRepositoriesForUser(username: String): List<RepositoryModel>
    suspend fun getRepository(owner: String, repo: String): RepositoryModel
}