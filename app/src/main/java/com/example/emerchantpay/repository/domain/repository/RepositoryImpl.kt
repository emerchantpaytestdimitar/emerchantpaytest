package com.example.emerchantpay.repository.domain.repository

import android.util.Log
import com.example.emerchantpay.account.domain.model.User
import com.example.emerchantpay.common.AppDatabase
import com.example.emerchantpay.repository.data.remote.RepositoryService
import com.example.emerchantpay.repository.domain.model.ConverterRepositoryUtil.convertModelToRepositoryAndOwner
import com.example.emerchantpay.repository.domain.model.ConverterRepositoryUtil.convertRepositoryModelToDb
import com.example.emerchantpay.repository.domain.model.ConverterRepositoryUtil.getRepositoriesModelByOwnerId
import com.example.emerchantpay.repository.domain.model.RepositoryModel
import com.example.emerchantpay.repository.domain.model.RepositorySearchResponse
import okhttp3.Credentials
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RepositoryImpl(
    private val repositoryService: RepositoryService,
    private val db: AppDatabase
) : Repository {

    override suspend fun getStarredRepositoriesForAuthenticatedUser(
        user: User,
        token: String
    ): List<RepositoryModel> {
        val response =
            repositoryService.getRepositoriesForAuthenticatedUser(user = user.login, token = token)
        if (response.isSuccessful) {
            var ownerId = user.id
            db.repositoryModelDao().deleteAllReposByStarredByUserId(ownerId)
            response.body()?.let { repositories ->
                for (repository in repositories) {
                    repository.starredByUserId = user.id
                    val repositoryModelAndOwner = convertModelToRepositoryAndOwner(repository)
                    db.repositoryModelDao().insert(repositoryModelAndOwner)
                }
                return getRepositoriesModelByOwnerId(ownerId, db.repositoryModelDao())
            }
            return listOf()
        }
        return listOf()
    }

    override suspend fun getRepositoriesForUnAuthenticatedUser(
        user: User,
    ): List<RepositoryModel> {
        val response =
            repositoryService.getRepositoriesForUnauthenticatedUser(user = user.login)
        if (response.isSuccessful) {
            var ownerId = user.id
            response.body()?.let { repositories ->
                for (repository in repositories) {
                    val repositoryModelAndOwner = convertModelToRepositoryAndOwner(repository)
                    db.repositoryModelDao().deleteAllReposByStarredByUserId(ownerId)
                    db.repositoryModelDao().insert(repositoryModelAndOwner)
                }
                return getRepositoriesModelByOwnerId(ownerId, db.repositoryModelDao())
            }
            return listOf()
        }
        return listOf()
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

    override suspend fun searchRepositories(
        token: String,
        query: String
    ): List<RepositoryModel> {
        return repositoryService.searchRepositories(query = query, token = "Bearer $token").items
    }

}