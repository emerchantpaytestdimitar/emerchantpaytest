package com.example.emerchantpay.account.di

import com.example.emerchantpay.account.data.remote.ProfileService
import com.example.emerchantpay.account.data.TokenService
import com.example.emerchantpay.account.domain.repository.AccountRepository
import com.example.emerchantpay.account.domain.repository.AccountRepositoryImpl
import com.example.emerchantpay.account.presentation.viewmodel.AccountViewModel
import com.example.emerchantpay.common.constants.ModuleConstants
import com.example.emerchantpay.common.di.retrofitModule
import com.example.emerchantpay.common.di.roomModule
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit

val accountModule = module {
    includes(roomModule, retrofitModule)

    single { get<Retrofit>(named(ModuleConstants.MODULE_NAME_RETROFIT_TOKEN)).create(TokenService::class.java) }
    single { get<Retrofit>(named(ModuleConstants.MODULE_NAME_RETROFIT_API)).create(ProfileService::class.java) }
    single<AccountRepository> { AccountRepositoryImpl(get(), get()) }

    viewModel { AccountViewModel(get()) }
}