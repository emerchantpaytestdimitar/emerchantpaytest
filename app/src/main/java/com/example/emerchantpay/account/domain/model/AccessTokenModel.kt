package com.example.emerchantpay.account.domain.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.emerchantpay.data.constants.AccessTokenJsonConstants
import com.example.emerchantpay.data.constants.AccessTokenRoomConstants
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class AccessTokenModel(
    @SerializedName(AccessTokenJsonConstants.ACCESS_TOKEN)
    @PrimaryKey val accessToken: String,

    @SerializedName(AccessTokenJsonConstants.TOKEN_TYPE)
    val tokenType: String,

    @SerializedName(AccessTokenJsonConstants.SCOPE)
    val scope: String,
) : Parcelable
