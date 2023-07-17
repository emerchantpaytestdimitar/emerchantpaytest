package com.example.emerchantpay.repository.di

import com.example.emerchantpay.account.data.ProfileService
import com.example.emerchantpay.account.presentation.viewmodel.AccountViewModel
import com.example.emerchantpay.data.di.retrofitModule
import com.example.emerchantpay.data.di.roomModule
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
    single { get<Retrofit>(named("login")).create(RepositoryService::class.java) }
    single<Repository> { RepositoryImpl(get()) }

    viewModel { RepositoryViewModel(get()) }
}