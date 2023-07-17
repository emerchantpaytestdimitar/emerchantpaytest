package com.example.emerchantpay.repository.domain.model

import androidx.room.Embedded
import androidx.room.Relation

data class RepositoryAndOwner(
    @Embedded
    val repository: RepositoryModelDb,

    @Relation(parentColumn = "ownerId", entityColumn = "id")
    val owner: RepositoryOwner
)
