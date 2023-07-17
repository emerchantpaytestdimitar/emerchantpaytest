package com.example.emerchantpay.repository.domain

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.example.emerchantpay.data.constants.RepositoryConstants
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Entity(tableName = "table_repositories")
@Parcelize
data class RepositoryModel(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo("id_db")
    val id_db: Long,
    @SerializedName(RepositoryConstants.NAME)
    @ColumnInfo(name = RepositoryConstants.NAME, defaultValue = "")
    val name: String,
    @SerializedName(RepositoryConstants.ID)
    @ColumnInfo(name = RepositoryConstants.ID, defaultValue = "0")
    val id: Int,
) : Parcelable