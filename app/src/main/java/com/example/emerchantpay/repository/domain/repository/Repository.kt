package com.example.emerchantpay.repository.domain.repository

import com.example.emerchantpay.repository.domain.RepositoryModel

interface Repository {

    suspend fun getRepositoriesForUser(username: String): List<RepositoryModel>
}