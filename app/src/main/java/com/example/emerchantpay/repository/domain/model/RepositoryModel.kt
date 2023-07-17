package com.example.emerchantpay.repository.domain.model

import android.os.Parcelable
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class RepositoryModel(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("full_name")
    val fullName: String,
    val ownerId: Int,
    @SerializedName("description")
    val description: String?,
    @SerializedName("owner")
    val owner: RepositoryOwner
) : Parcelable