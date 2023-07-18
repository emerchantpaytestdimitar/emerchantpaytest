package com.example.emerchantpay.repository.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.emerchantpay.account.domain.model.User
import com.example.emerchantpay.repository.domain.model.RepositoryModel
import com.example.emerchantpay.repository.domain.repository.Repository
import kotlinx.coroutines.launch

class RepositoryViewModel(private val repository: Repository) : ViewModel() {

    private val repositoriesMutableLiveData: MutableLiveData<List<RepositoryModel>> =
        MutableLiveData()
    val repositoriesLiveData: LiveData<List<RepositoryModel>> get() = repositoriesMutableLiveData

    private val repositoryMutableLiveData: MutableLiveData<RepositoryModel> = MutableLiveData()
    val repositoryLiveData: LiveData<RepositoryModel> get() = repositoryMutableLiveData

    private val listFollowersMutableLiveData: MutableLiveData<List<User>> = MutableLiveData()
    val listFollowersLiveData: LiveData<List<User>> get() = listFollowersMutableLiveData

    private val contributorsMutableLiveData: MutableLiveData<List<User>> = MutableLiveData()
    val contributorsLiveData: LiveData<List<User>> get() = contributorsMutableLiveData
    fun getRepositoriesForUser(user: String) = viewModelScope.launch {
        repositoriesMutableLiveData.value = repository.getRepositoriesForUser(user)
    }

    fun getRepository(owner: String, repo: String) = viewModelScope.launch {
        repositoryMutableLiveData.value = repository.getRepository(owner, repo)
    }

    fun listRepoContributors(
        owner: String, repo: String
    ) = viewModelScope.launch {
        contributorsMutableLiveData.value = repository.listRepoContributors(owner = owner, repo = repo)
    }

    fun listFollowers(user: String) = viewModelScope.launch {
        listFollowersMutableLiveData.value = repository.listFollowers(user)
    }
}