package com.example.emerchantpay.repository.domain.repository

import com.example.emerchantpay.account.domain.model.User
import com.example.emerchantpay.repository.domain.model.RepositoryModel

interface Repository {

    suspend fun getStarredRepositoriesForAuthenticatedUser(username: String, token: String): List<RepositoryModel>
    suspend fun getRepositoriesForUnAuthenticatedUser(username: String): List<RepositoryModel>
    suspend fun getRepository(owner: String, repo: String): RepositoryModel
    suspend fun listRepoContributors(
        owner: String, repo: String
    ): List<User?>?

    suspend fun starRepo(owner: String, repo: String, token: String): Boolean
    suspend fun unStarRepo(owner: String, repo: String, token: String): Boolean

    suspend fun checkIfRepoIsStarred(token: String, owner: String, repo: String): Boolean

    suspend fun searchRepositories(
        token: String, query: String
    ): List<RepositoryModel>
}