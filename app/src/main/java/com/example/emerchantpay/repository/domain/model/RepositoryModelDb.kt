package com.example.emerchantpay.repository.domain.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.example.emerchantpay.data.constants.RepositoryConstants
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(
    tableName = "table_repositories"
)
data class RepositoryModelDb(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "repoId")
    val repoId: Int,

    @ColumnInfo(name = "name")
    val name: String,

    @ColumnInfo(name = "full_name")
    val fullName: String,

    @ColumnInfo(name = "ownerId")
    val ownerId: Int,

    @ColumnInfo(name = "description")
    val description: String?,

    @ColumnInfo(name = "starredByUserId")
    var starredByUserId: Long = -2
) : Parcelable
