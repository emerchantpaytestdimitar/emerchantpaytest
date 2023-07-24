package com.example.emerchantpaytest

import android.arch.core.executor.testing.InstantTaskExecutorRule
import android.content.Context
import androidx.room.Room
import androidx.arch.core.executor.*
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import com.example.emerchantpay.common.AppDatabase
import com.example.emerchantpay.repository.data.local.RepositoryAndOwner
import com.example.emerchantpay.repository.data.local.RepositoryModelDao
import com.example.emerchantpay.repository.domain.model.RepositoryModelDb
import com.example.emerchantpay.repository.domain.model.RepositoryOwner
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.After
import org.junit.Test
import org.junit.runner.RunWith

@ExperimentalCoroutinesApi
@LargeTest
@RunWith(AndroidJUnit4::class)
class RepositoryModelDaoTest {

    private lateinit var repositoryModelDao: RepositoryModelDao
    private lateinit var db: AppDatabase

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun createDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(
            context, AppDatabase::class.java
        ).allowMainThreadQueries().build()
        repositoryModelDao = db.repositoryModelDao()
    }

    @After
    fun closeDb() {
        db.close()
    }

    @Test
    fun testInsertRepositoryAndOwner() = runBlocking {
        val repositoryDb = RepositoryModelDb(
            repoId = 1,
            name = "repoName",
            fullName = "repoFullName",
            ownerId = 1,
            description = "repoDescription",
            starredByUserId = 1
        )
        val owner = RepositoryOwner(ownerId = 1, login = "ownerLogin")
        val repositoryAndOwner = RepositoryAndOwner(repositoryDb, owner)
        repositoryModelDao.insert(repositoryAndOwner)

        val allRepositories = repositoryModelDao.getAllRepositories()

        assertEquals(allRepositories[0], repositoryDb)
    }

    @Test
    fun testSearchRepositoryByName() = runBlocking {
        val repositoryDb = RepositoryModelDb(
            repoId = 1,
            name = "repoName",
            fullName = "repoFullName",
            ownerId = 1,
            description = "repoDescription",
            starredByUserId = 1
        )
        val owner = RepositoryOwner(ownerId = 1, login = "ownerLogin")
        val repositoryAndOwner = RepositoryAndOwner(repositoryDb, owner)
        repositoryModelDao.insert(repositoryAndOwner)

        val searchResult = repositoryModelDao.searchRepositoryByName(repositoryDb.name)

        assertEquals(searchResult[0].repository, repositoryDb)
    }

    @Test
    fun testGetRepositoriesAndOwnerByOwnerId() = runBlocking {
        val repositoryDb = RepositoryModelDb(
            repoId = 1,
            name = "repoName",
            fullName = "repoFullName",
            ownerId = 1,
            description = "repoDescription",
            starredByUserId = 1
        )
        val owner = RepositoryOwner(ownerId = 1, login = "ownerLogin")
        val repositoryAndOwner = RepositoryAndOwner(repositoryDb, owner)
        repositoryModelDao.insert(repositoryAndOwner)

        val repositoriesAndOwner =
            repositoryModelDao.getRepositoriesAndOwnerByOwnerId(repositoryDb.starredByUserId)

        assertEquals(repositoriesAndOwner[0].repository, repositoryDb)
        assertEquals(repositoriesAndOwner[0].owner, owner)
    }
}
