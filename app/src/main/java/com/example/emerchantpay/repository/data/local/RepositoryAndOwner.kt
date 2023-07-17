package com.example.emerchantpay.repository.data.local

import androidx.room.Embedded
import androidx.room.Relation
import com.example.emerchantpay.repository.domain.model.RepositoryModel
import com.example.emerchantpay.repository.domain.model.RepositoryOwner

data class RepositoryAndOwner(
    @Embedded
    val repository: RepositoryModel,

    @Relation(parentColumn = "ownerId", entityColumn = "id")
    val owner: RepositoryOwner
)
