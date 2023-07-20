package com.example.emerchantpay.repository.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.emerchantpay.account.domain.model.User
import com.example.emerchantpay.repository.domain.model.RepositoryModel
import com.example.emerchantpay.repository.domain.repository.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RepositoryViewModel(private val repository: Repository) : ViewModel() {

    private val repositoriesMutableLiveData: MutableLiveData<List<RepositoryModel>> =
        MutableLiveData()
    val repositoriesLiveData: LiveData<List<RepositoryModel>> get() = repositoriesMutableLiveData

    private val searchRepositoriesMutableLiveData: MutableLiveData<List<RepositoryModel>> =
        MutableLiveData()
    val searchRepositoriesLiveData: LiveData<List<RepositoryModel>> get() = searchRepositoriesMutableLiveData

    private val repositoryMutableLiveData: MutableLiveData<RepositoryModel> = MutableLiveData()
    val repositoryLiveData: LiveData<RepositoryModel> get() = repositoryMutableLiveData

    private val contributorsMutableLiveData: MutableLiveData<List<User?>?> = MutableLiveData()
    val contributorsLiveData: LiveData<List<User?>?> get() = contributorsMutableLiveData

    private val starRepoMutableLiveData: MutableLiveData<Boolean> = MutableLiveData()
    val starRepoLiveData: LiveData<Boolean> get() = starRepoMutableLiveData

    private val unStarRepoMutableLiveData: MutableLiveData<Boolean> = MutableLiveData()
    val unsStarRepoLiveData: LiveData<Boolean> get() = unStarRepoMutableLiveData

    private val checkIfRepoIsStarredMutableLiveData: MutableLiveData<Boolean> = MutableLiveData()
    val checkIfRepoIsStarredLiveData: LiveData<Boolean> get() = checkIfRepoIsStarredMutableLiveData

    fun getStarredRepositoriesForAuthenticatedUser(user: User, token: String) =
        viewModelScope.launch(Dispatchers.IO) {
            repositoriesMutableLiveData.postValue(
                repository.getStarredRepositoriesForAuthenticatedUser(
                    user = user,
                    token = token
                )
            )
        }

    fun getRepositoriesForUnAuthenticatedUser(user: User) = viewModelScope.launch(Dispatchers.IO) {
        repositoriesMutableLiveData.postValue(repository.getRepositoriesForUnAuthenticatedUser(user = user))
    }

    fun searchRepositories(query: String, token: String) = viewModelScope.launch(Dispatchers.IO) {
        searchRepositoriesMutableLiveData.postValue(
            repository.searchRepositories(query = query, token = token)
        )
    }

    fun getRepository(owner: String, repo: String) = viewModelScope.launch(Dispatchers.IO) {
        repositoryMutableLiveData.postValue(repository.getRepository(owner, repo))
    }

    fun listRepoContributors(
        owner: String, repo: String
    ) = viewModelScope.launch(Dispatchers.IO) {
        contributorsMutableLiveData.postValue(
            repository.listRepoContributors(
                owner = owner,
                repo = repo
            )
        )
    }

    fun starRepo(owner: String, repo: String, token: String) =
        viewModelScope.launch(Dispatchers.IO) {
            starRepoMutableLiveData.postValue(
                repository.starRepo(
                    owner = owner,
                    repo = repo,
                    token = token
                )
            )
        }

    fun unStarRepo(owner: String, repo: String, token: String) =
        viewModelScope.launch(Dispatchers.IO) {
            unStarRepoMutableLiveData.postValue(
                repository.unStarRepo(owner = owner, repo = repo, token = token)
            )
        }

    fun checkIfRepoIsStarred(owner: String, repo: String, token: String) =
        viewModelScope.launch(Dispatchers.IO) {
            checkIfRepoIsStarredMutableLiveData.postValue(
                repository.checkIfRepoIsStarred(
                    owner = owner,
                    repo = repo,
                    token = token
                )
            )
        }

}