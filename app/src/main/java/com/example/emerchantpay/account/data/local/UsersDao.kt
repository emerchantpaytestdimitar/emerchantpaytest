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
    fun insertUser(user: UserDb)

    @Update
    fun updateUser(user: UserDb)

    @Delete
    fun deleteUser(user: UserDb)

    @Query("SELECT * FROM users WHERE login LIKE '%' || :name || '%'")
    fun searchUserByName(name: String): List<UserDb>?

    @Query("SELECT * FROM users WHERE id = :id")
    fun getUserById(id: Long): UserDb

    @Query("DELETE FROM users WHERE id = :id")
    fun deleteUserById(id: Long)

    @Transaction
    @Query("SELECT * FROM users WHERE followed_by_user_id = :followedByUserId")
    fun getUsersByOwnerId(followedByUserId: Long): List<UserDb>

    @Query("DELETE FROM users WHERE followed_by_user_id = :followedByUserId")
    fun deleteAllUsersByFollowedByUserId(followedByUserId: Long)

}