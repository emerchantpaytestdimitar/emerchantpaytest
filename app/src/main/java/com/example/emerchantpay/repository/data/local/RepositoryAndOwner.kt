package com.example.emerchantpay.repository.data.local

import androidx.room.Embedded
import androidx.room.Relation
import com.example.emerchantpay.repository.domain.model.RepositoryModel
import com.example.emerchantpay.repository.domain.model.RepositoryModelDb
import com.example.emerchantpay.repository.domain.model.RepositoryOwner

data class RepositoryAndOwner(
    @Embedded
    val repository: RepositoryModelDb,

    @Relation(
        parentColumn = "ownerId",
        entityColumn = "ownerId"
    )
    val owner: RepositoryOwner
)
