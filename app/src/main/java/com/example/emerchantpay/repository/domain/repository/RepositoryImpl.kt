package com.example.emerchantpay.repository.domain.repository

import android.util.Log
import com.example.emerchantpay.account.domain.model.User
import com.example.emerchantpay.repository.data.remote.RepositoryService
import com.example.emerchantpay.repository.domain.model.RepositoryModel
import okhttp3.Credentials
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

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

    override suspend fun starRepo(owner: String, repo: String, token: String): Boolean {
        val response =
            repositoryService.starRepo(
                owner = owner,
                repo = repo,
                token = "Bearer $token"
            )
        return response.isSuccessful
    }

    override suspend fun unStarRepo(owner: String, repo: String, token: String): Boolean {
        val response =
            repositoryService.unStarRepo(
                owner = owner,
                repo = repo,
                token = "Bearer $token"
            )
        return response.isSuccessful
    }

    override suspend fun checkIfRepoIsStarred(token: String, owner: String, repo: String): Boolean {
        val response =
            repositoryService.checkIfRepoIsStarred(
                owner = owner,
                repo = repo,
                token = "Bearer $token"
            )
        return if (response.code() == 204) {
            true
        } else if (response.code() == 404) {
            false
        } else {
            false
        }
    }

}