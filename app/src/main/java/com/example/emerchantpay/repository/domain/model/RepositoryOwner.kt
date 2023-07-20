package com.example.emerchantpay.repository.domain.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
@Parcelize
@Entity(
    tableName = "owner"
)
data class RepositoryOwner(
    @PrimaryKey(autoGenerate = false)
    @SerializedName("id")
    @ColumnInfo(name = "ownerId")
    val ownerId: Int,

    @SerializedName("login")
    @ColumnInfo(name = "login")
    val login: String
) : Parcelable
