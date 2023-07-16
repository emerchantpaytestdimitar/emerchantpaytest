package com.example.emerchantpay.account.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.emerchantpay.account.domain.model.User
import com.example.emerchantpay.account.domain.repository.AccountRepository
import kotlinx.coroutines.launch

class AccountViewModel(private val repository: AccountRepository) : ViewModel() {

    private val accessTokenMutableLiveData: MutableLiveData<String> = MutableLiveData()
    val accessTokenLiveData: LiveData<String> get() = accessTokenMutableLiveData

    private val userMutableLiveData: MutableLiveData<User> = MutableLiveData()
    val userLiveData: LiveData<User> get() = userMutableLiveData

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
}