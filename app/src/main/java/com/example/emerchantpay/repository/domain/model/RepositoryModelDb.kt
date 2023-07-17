package com.example.emerchantpay.repository.domain.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.example.emerchantpay.data.constants.RepositoryConstants
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Entity(
    tableName = "table_repositories",
    foreignKeys = [
        ForeignKey(
            entity = RepositoryOwner::class,
            parentColumns = ["id"],
            childColumns = ["ownerId"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
@Parcelize
data class RepositoryModelDb(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo("id_db")
    val id_db: Long,
    @SerializedName("id")
    @ColumnInfo(name = RepositoryConstants.ID)
    val id: Int,
    @SerializedName("name")
    @ColumnInfo(name = RepositoryConstants.NAME)
    val name: String,
    @SerializedName("full_name")
    val fullName: String,
    @ColumnInfo(index = true, name = "ownerId")
    val ownerId: Int,
    @ColumnInfo(name = RepositoryConstants.DESCRIPTION)
    @SerializedName("description")
    val description: String?
) : Parcelable
