package com.example.emerchantpay.account.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.emerchantpay.account.domain.model.User
import com.example.emerchantpay.account.domain.repository.AccountRepository
import com.example.emerchantpay.account.domain.repository.AccountRepositoryImpl
import kotlinx.coroutines.launch

class AccountViewModel(private val repository: AccountRepository) : ViewModel() {

    private val accessTokenMutableLiveData: MutableLiveData<String> = MutableLiveData()
    val accessTokenLiveData: LiveData<String> get() = accessTokenMutableLiveData

    private val userMutableLiveData: MutableLiveData<User> = MutableLiveData()
    val userLiveData: LiveData<User> get() = userMutableLiveData

    private val listFollowersMutableLiveData: MutableLiveData<List<User>> = MutableLiveData()
    val listFollowersLiveData: LiveData<List<User>> get() = listFollowersMutableLiveData

    private val listFollowingMutableLiveData: MutableLiveData<List<User>> = MutableLiveData()
    val listFollowingsLiveData: LiveData<List<User>> get() = listFollowingMutableLiveData
    fun getToken(code: String) = viewModelScope.launch {
        try {
            val token = repository.getToken(code)
            token?.let {
                accessTokenMutableLiveData.value = it
            }
        } catch (e: Exception) {
            Log.e(e.toString(), e.toString(), e)
        }
    }

    fun performLogin(token: String) = viewModelScope.launch {
        val user = repository.performLogin(token)
        user?.let {
            userMutableLiveData.value = it
        }
    }

    fun listFollowers(user: String, token: String) = viewModelScope.launch {
        listFollowersMutableLiveData.value = repository.listFollowers(user, token)
    }

    fun listFollowing(user: String, token: String)  = viewModelScope.launch {
        listFollowingMutableLiveData.value = repository.listFollowing(user, token)
    }
}