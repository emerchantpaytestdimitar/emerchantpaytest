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

    private val contributorsMutableLiveData: MutableLiveData<List<User?>?> = MutableLiveData()
    val contributorsLiveData: LiveData<List<User?>?> get() = contributorsMutableLiveData

    private val starRepoMutableLiveData: MutableLiveData<Boolean> = MutableLiveData()
    val starRepoLiveData: LiveData<Boolean> get() = starRepoMutableLiveData

    private val unStarRepoMutableLiveData: MutableLiveData<Boolean> = MutableLiveData()
    val unsStarRepoLiveData: LiveData<Boolean> get() = unStarRepoMutableLiveData

    fun getRepositoriesForUser(user: String) = viewModelScope.launch {
        repositoriesMutableLiveData.value = repository.getRepositoriesForUser(user)
    }

    fun getRepository(owner: String, repo: String) = viewModelScope.launch {
        repositoryMutableLiveData.value = repository.getRepository(owner, repo)
    }

    fun listRepoContributors(
        owner: String, repo: String
    ) = viewModelScope.launch {
        contributorsMutableLiveData.value =
            repository.listRepoContributors(owner = owner, repo = repo)
    }

    fun starRepo(owner: String, repo: String, token: String) = viewModelScope.launch {
        starRepoMutableLiveData.value = repository.starRepo(owner = owner, repo = repo, token)
    }

    fun unStarRepo(owner: String, repo: String, token: String) = viewModelScope.launch {
        unStarRepoMutableLiveData.value = repository.unStarRepo(owner = owner, repo = repo, token)
    }
}