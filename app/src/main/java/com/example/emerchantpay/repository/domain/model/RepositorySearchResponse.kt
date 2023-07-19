package com.example.emerchantpay.repository.domain.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class RepositorySearchResponse(
    @SerializedName("total_count") val totalCount: Int,
    @SerializedName("items") val items: List<RepositoryModel>
) : Parcelable
