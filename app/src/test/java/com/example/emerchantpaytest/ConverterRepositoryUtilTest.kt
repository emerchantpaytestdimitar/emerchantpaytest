package com.example.emerchantpaytest

import com.example.emerchantpay.repository.data.local.RepositoryAndOwner
import com.example.emerchantpay.repository.domain.model.ConverterRepositoryUtil
import com.example.emerchantpay.repository.domain.model.RepositoryModel
import com.example.emerchantpay.repository.domain.model.RepositoryModelDb
import com.example.emerchantpay.repository.domain.model.RepositoryOwner
import org.junit.Before
import org.junit.jupiter.api.Assertions
import org.junit.Test

class ConverterRepositoryUtilTest {
    private lateinit var repositoryModel: RepositoryModel
    private lateinit var repositoryModelDb: RepositoryModelDb
    private lateinit var repositoryOwner: RepositoryOwner
    private lateinit var repositoryAndOwner: RepositoryAndOwner

    @Before
    fun setup() {
        repositoryOwner = RepositoryOwner(ownerId = 1, login = "ownerLogin")
        repositoryModel = RepositoryModel(
            id = 1,
            name = "repoName",
            fullName = "repoFullName",
            description = "repoDescription",
            owner = repositoryOwner,
            starredByUserId = 1
        )
        repositoryModelDb = RepositoryModelDb(
            repoId = 1,
            name = "repoName",
            fullName = "repoFullName",
            ownerId = 1,
            description = "repoDescription",
            starredByUserId = 1
        )
        repositoryAndOwner = RepositoryAndOwner(
            repository = repositoryModelDb,
            owner = repositoryOwner
        )
    }

    @Test
    fun `test convertRepositoryModelToDb`() {
        val result = ConverterRepositoryUtil.convertRepositoryModelToDb(repositoryModel)
        Assertions.assertEquals(repositoryModelDb, result)
    }

    @Test
    fun `test repositoryAndOwnerToModel`() {
        val result = ConverterRepositoryUtil.repositoryAndOwnerToModel(repositoryAndOwner)
        Assertions.assertEquals(repositoryModel, result)
    }

    @Test
    fun `test extractOwnerFromModel`() {
        val result = ConverterRepositoryUtil.extractOwnerFromModel(repositoryModel)
        Assertions.assertEquals(repositoryOwner, result)
    }

    @Test
    fun `test convertModelToRepositoryAndOwner`() {
        val result = ConverterRepositoryUtil.convertModelToRepositoryAndOwner(repositoryModel)
        Assertions.assertEquals(repositoryAndOwner, result)
    }
}
