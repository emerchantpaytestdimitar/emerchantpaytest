package com.example.emerchantpay.repository.di

import com.example.emerchantpay.common.constants.ModuleConstants
import com.example.emerchantpay.common.di.retrofitModule
import com.example.emerchantpay.common.di.roomModule
import com.example.emerchantpay.repository.data.remote.RepositoryService
import com.example.emerchantpay.repository.domain.repository.Repository
import com.example.emerchantpay.repository.domain.repository.RepositoryImpl
import com.example.emerchantpay.repository.presentation.viewmodel.RepositoryViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit

val repositoryModule = module {
    includes(roomModule, retrofitModule)
    single { get<Retrofit>(named(ModuleConstants.MODULE_NAME_RETROFIT_API)).create(RepositoryService::class.java) }
    single<Repository> { RepositoryImpl(get(), get()) }

    viewModel { RepositoryViewModel(get()) }
}