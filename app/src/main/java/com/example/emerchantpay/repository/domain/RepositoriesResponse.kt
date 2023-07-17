package com.example.emerchantpay.repository.domain

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class RepositoriesResponse (
    val repositories: List<RepositoryModel>
): Parcelable
