package com.example.emerchantpay.common

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.emerchantpay.account.domain.model.User
import com.example.emerchantpay.repository.data.local.RepositoryModelDao
import com.example.emerchantpay.repository.domain.model.RepositoryModel
import com.example.emerchantpay.repository.domain.model.RepositoryModelDb
import com.example.emerchantpay.repository.domain.model.RepositoryOwner

@Database(entities = [RepositoryModelDb::class, RepositoryOwner::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun repositoryModelDao(): RepositoryModelDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "emerchantpay_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}
