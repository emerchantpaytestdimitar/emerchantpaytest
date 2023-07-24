package com.example.emerchantpay.repository.domain.model

import com.example.emerchantpay.repository.data.local.RepositoryAndOwner
import com.example.emerchantpay.repository.data.local.RepositoryModelDao

object ConverterRepositoryUtil {

    fun convertRepositoryModelToDb(repositoryModel: RepositoryModel): RepositoryModelDb {
        return RepositoryModelDb(
            repoId = repositoryModel.id,
            name = repositoryModel.name,
            fullName = repositoryModel.fullName,
            ownerId = repositoryModel.owner.ownerId,
            description = repositoryModel.description,
            starredByUserId = repositoryModel.starredByUserId
        )
    }

    fun repositoryAndOwnerToModel(repositoryAndOwner: RepositoryAndOwner): RepositoryModel {
        return RepositoryModel(
            id = repositoryAndOwner.repository.ownerId,
            name = repositoryAndOwner.repository.name,
            fullName = repositoryAndOwner.repository.fullName,
            description = repositoryAndOwner.repository.description,
            starredByUserId = repositoryAndOwner.repository.starredByUserId,
            owner = RepositoryOwner(
                ownerId = repositoryAndOwner.owner.ownerId,
                login = repositoryAndOwner.owner.login
            )
        )
    }

    suspend fun getRepositoriesModelByOwnerId(
        ownerId: Long,
        repositoryModelDao: RepositoryModelDao
    ): List<RepositoryModel> {
        val repositoriesAndOwnerList = repositoryModelDao.getRepositoriesAndOwnerByOwnerId(ownerId)
        return repositoriesAndOwnerList.map { repositoryAndOwner ->
            repositoryAndOwnerToModel(
                repositoryAndOwner
            )
        }
    }

    fun extractOwnerFromModel(repositoryModel: RepositoryModel): RepositoryOwner {
        return RepositoryOwner(
            ownerId = repositoryModel.owner.ownerId,
            login = repositoryModel.owner.login
        )
    }

    fun convertModelToRepositoryAndOwner(repositoryModel: RepositoryModel): RepositoryAndOwner {
        val repositoryModelDb = convertRepositoryModelToDb(repositoryModel)
        val repositoryOwner = extractOwnerFromModel(repositoryModel)
        return RepositoryAndOwner(
            repository = repositoryModelDb,
            owner = repositoryOwner
        )
    }
}