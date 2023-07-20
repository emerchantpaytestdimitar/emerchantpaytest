package com.example.emerchantpay.common

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.emerchantpay.account.data.local.UserDb
import com.example.emerchantpay.account.data.local.UsersDao
import com.example.emerchantpay.repository.data.local.RepositoryModelDao
import com.example.emerchantpay.repository.domain.model.RepositoryModelDb
import com.example.emerchantpay.repository.domain.model.RepositoryOwner

@Database(entities = [UserDb::class, RepositoryModelDb::class, RepositoryOwner::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun repositoryModelDao(): RepositoryModelDao
    abstract fun usesrDao(): UsersDao

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
