package com.example.emerchantpay.account.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.emerchantpay.account.domain.model.User
import com.example.emerchantpay.account.domain.repository.AccountRepository
import com.example.emerchantpay.account.domain.repository.AccountRepositoryImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AccountViewModel(private val repository: AccountRepository) : ViewModel() {

    private val accessTokenMutableLiveData: MutableLiveData<String> = MutableLiveData()
    val accessTokenLiveData: LiveData<String> get() = accessTokenMutableLiveData

    private val userMutableLiveData: MutableLiveData<User> = MutableLiveData()
    val userLiveData: LiveData<User> get() = userMutableLiveData

    private val getUserMutableLiveData: MutableLiveData<User> = MutableLiveData()
    val getUserLiveData: LiveData<User> get() = getUserMutableLiveData

    private val listFollowersMutableLiveData: MutableLiveData<List<User>> = MutableLiveData()
    val listFollowersLiveData: LiveData<List<User>> get() = listFollowersMutableLiveData

    private val listFollowingMutableLiveData: MutableLiveData<List<User>> = MutableLiveData()
    val listFollowingsLiveData: LiveData<List<User>> get() = listFollowingMutableLiveData

    private val searchUsersMutableLiveData: MutableLiveData<List<User>> = MutableLiveData()
    val searchUsersLiveData: LiveData<List<User>> get() = searchUsersMutableLiveData

    fun getToken(code: String) = viewModelScope.launch(Dispatchers.IO) {
        try {
            val token = repository.getToken(code)
            token?.let {
                accessTokenMutableLiveData.postValue(it)
            }
        } catch (e: Exception) {
            Log.e(e.toString(), e.toString(), e)
        }
    }

    fun performLogin(token: String) = viewModelScope.launch(Dispatchers.IO) {
        val user = repository.performLogin(token)
        user?.let {
            userMutableLiveData.postValue(it)
        }
    }

    fun listFollowers(user: User, token: String) = viewModelScope.launch(Dispatchers.IO) {
        listFollowersMutableLiveData.postValue(repository.listFollowers(user, token))
    }

    fun listFollowing(user: User, token: String) = viewModelScope.launch(Dispatchers.IO) {
        listFollowingMutableLiveData.postValue(repository.listFollowing(user, token))
    }

    fun searchUsers(query: String, token: String) = viewModelScope.launch(Dispatchers.IO) {
        searchUsersMutableLiveData.postValue(repository.searchUsers(query = query, token = token))
    }

    fun getUser(userId: String, token: String) = viewModelScope.launch(Dispatchers.IO) {
        getUserMutableLiveData.postValue(repository.getUser(userId = userId, token = token))
    }
}