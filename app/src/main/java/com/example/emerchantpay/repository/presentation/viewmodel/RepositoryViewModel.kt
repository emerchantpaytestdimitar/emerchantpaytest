package com.example.emerchantpay.repository.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.emerchantpay.account.domain.model.User
import com.example.emerchantpay.account.domain.repository.AccountRepositoryImpl
import com.example.emerchantpay.repository.domain.RepositoryModel
import com.example.emerchantpay.repository.domain.repository.Repository
import kotlinx.coroutines.launch

class RepositoryViewModel(private val repository: Repository) : ViewModel() {

    private val repositoriesMutableLiveData: MutableLiveData<List<RepositoryModel>> = MutableLiveData()
    val repositoriesLiveData: LiveData<List<RepositoryModel>> get() = repositoriesMutableLiveData

//    private val userMutableLiveData: MutableLiveData<User> = MutableLiveData()
//    val userLiveData: LiveData<User> get() = userMutableLiveData

    fun getRepositoriesForUser(user: String) =  viewModelScope.launch {
        repositoriesMutableLiveData.value = repository.getRepositoriesForUser(user)
    }
}