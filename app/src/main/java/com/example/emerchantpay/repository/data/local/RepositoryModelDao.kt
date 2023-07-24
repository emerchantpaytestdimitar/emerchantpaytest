package com.example.emerchantpay.repository.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import com.example.emerchantpay.repository.domain.model.RepositoryModelDb
import com.example.emerchantpay.repository.domain.model.RepositoryOwner

@Dao
interface RepositoryModelDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertRepository(repository: RepositoryModelDb?)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertOwner(owner: RepositoryOwner?)

    @Update
    fun updateRepository(repository: RepositoryModelDb)

    @Update
    fun updateOwner(owner: RepositoryOwner?)

    @Delete
    fun deleteRepository(repository: RepositoryModelDb)

    @Delete
    fun deleteOwner(owner: RepositoryOwner?)

    @Transaction
    fun insert(repositoryAndOwner: RepositoryAndOwner?) {
        insertOwner(repositoryAndOwner?.owner)
        insertRepository(repositoryAndOwner?.repository)
    }

    @Transaction
    fun update(repositoryAndOwner: RepositoryAndOwner) {
        updateOwner(repositoryAndOwner.owner)
        updateRepository(repositoryAndOwner.repository)
    }

    @Transaction
    fun delete(repositoryAndOwner: RepositoryAndOwner) {
        deleteRepository(repositoryAndOwner.repository)
        deleteOwner(repositoryAndOwner.owner)
    }

    @Transaction
    @Query("SELECT * FROM table_repositories WHERE name LIKE '%' || :name || '%'")
    fun searchRepositoryByName(name: String): List<RepositoryAndOwner>

    @Transaction
    @Query("SELECT * FROM table_repositories WHERE starredByUserId = :starredByUserId")
    fun getRepositoriesAndOwnerByOwnerId(starredByUserId: Long): List<RepositoryAndOwner>

    @Query("SELECT * FROM table_repositories")
    fun getAllRepositories(): List<RepositoryModelDb>

    @Query("DELETE FROM table_repositories WHERE starredByUserId = :starredByUserId")
    fun deleteAllReposByStarredByUserId(starredByUserId: Long)
}