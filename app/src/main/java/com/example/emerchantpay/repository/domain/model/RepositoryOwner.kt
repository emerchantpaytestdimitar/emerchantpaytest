package com.example.emerchantpay.repository.domain.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "owner", indices = [Index(value = ["id"], unique = true)])
data class RepositoryOwner(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo("id_db")
    val id_db: Long,

    @SerializedName("id")
    @ColumnInfo(name = "id")
    val id: Int,

    @SerializedName("login")
    @ColumnInfo(name = "login")
    val login: String
) : Parcelable
