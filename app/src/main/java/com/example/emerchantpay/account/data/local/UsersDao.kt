package com.example.emerchantpay.account.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update

@Dao
interface UsersDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertRepository(user: UserDb)

    @Update
    fun updateRepository(user: UserDb)

    @Delete
    fun deleteRepository(user: UserDb)

    @Transaction
    @Query("SELECT * FROM users WHERE followed_by_user_id = :followedByUserId")
    fun getRepositoriesAndOwnerByOwnerId(followedByUserId: Long): List<UserDb>

    @Query("DELETE FROM users WHERE followed_by_user_id = :followedByUserId")
    fun deleteAllReposByStarredByUserId(followedByUserId: Long)
}