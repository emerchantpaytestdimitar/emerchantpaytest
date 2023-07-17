package com.example.emerchantpay.account.di

import com.example.emerchantpay.account.data.ProfileService
import com.example.emerchantpay.account.data.TokenService
import com.example.emerchantpay.account.domain.repository.AccountRepository
import com.example.emerchantpay.account.domain.repository.AccountRepositoryImpl
import com.example.emerchantpay.account.presentation.viewmodel.AccountViewModel
import com.example.emerchantpay.data.di.retrofitModule
import com.example.emerchantpay.data.di.roomModule
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit

val accountModule = module {
    includes(roomModule, retrofitModule)

    single { get<Retrofit>(named("token")).create(TokenService::class.java) }
    single { get<Retrofit>(named("login")).create(ProfileService::class.java) }
    single<AccountRepository> { AccountRepositoryImpl(get(), get()) }

    viewModel { AccountViewModel(get()) }
}