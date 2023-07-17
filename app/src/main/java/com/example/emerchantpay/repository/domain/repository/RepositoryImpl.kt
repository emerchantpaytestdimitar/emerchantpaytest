package com.example.emerchantpay.repository.domain.repository

import com.example.emerchantpay.repository.data.remote.RepositoryService
import com.example.emerchantpay.repository.domain.RepositoryModel

class RepositoryImpl(private val repositoryService: RepositoryService) : Repository {

    override suspend fun getRepositoriesForUser(username: String): List<RepositoryModel> {
        return repositoryService.getRepositories(username)
    }
}