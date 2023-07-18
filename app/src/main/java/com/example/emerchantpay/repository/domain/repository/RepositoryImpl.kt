package com.example.emerchantpay.repository.domain.repository

import com.example.emerchantpay.account.domain.model.User
import com.example.emerchantpay.repository.data.remote.RepositoryService
import com.example.emerchantpay.repository.domain.model.RepositoryModel
import retrofit2.http.Path

class RepositoryImpl(private val repositoryService: RepositoryService) : Repository {

    override suspend fun getRepositoriesForUser(username: String): List<RepositoryModel> {
        return repositoryService.getRepositories(username)
    }

    override suspend fun getRepository(owner: String, repo: String): RepositoryModel {
        return repositoryService.getRepository(owner = owner, repo = repo)
    }

    override suspend fun listRepoContributors(
        owner: String, repo: String
    ): List<User> {
        val list = repositoryService.listRepoContributors(owner = owner, repo = repo)
        return if (!list.isNullOrEmpty()) {
            list
        } else listOf()
    }

    override suspend fun listFollowers(user: String): List<User> {
        return repositoryService.listFollowers(user)
    }

}