package com.example.emerchantpay.repository.domain.repository

import android.util.Log
import com.example.emerchantpay.account.domain.model.User
import com.example.emerchantpay.repository.data.remote.RepositoryService
import com.example.emerchantpay.repository.domain.model.RepositoryModel

class RepositoryImpl(private val repositoryService: RepositoryService) : Repository {

    override suspend fun getRepositoriesForUser(username: String): List<RepositoryModel> {
        return repositoryService.getRepositories(username)
    }

    override suspend fun getRepository(owner: String, repo: String): RepositoryModel {
        return repositoryService.getRepository(owner = owner, repo = repo)
    }

    override suspend fun listRepoContributors(
        owner: String, repo: String
    ): List<User?>? {
        return try {
            val list = repositoryService.listRepoContributors(owner = owner, repo = repo)
            if (!list.isNullOrEmpty()) {
                list
            } else listOf()
        } catch (e: KotlinNullPointerException) {
            Log.e(e.message, e.toString())
            listOf()
        }
    }

    override suspend fun listFollowers(user: String): List<User> {
        return repositoryService.listFollowers(user)
    }

}