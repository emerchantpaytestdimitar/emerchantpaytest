package com.example.emerchantpay.repository.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.emerchantpay.repository.domain.model.RepositoryModel
import com.example.emerchantpay.repository.domain.repository.Repository
import kotlinx.coroutines.launch

class RepositoryViewModel(private val repository: Repository) : ViewModel() {

    private val repositoriesMutableLiveData: MutableLiveData<List<RepositoryModel>> = MutableLiveData()
    val repositoriesLiveData: LiveData<List<RepositoryModel>> get() = repositoriesMutableLiveData

    private val repositoryMutableLiveData: MutableLiveData<RepositoryModel> = MutableLiveData()
     val repositoryLiveData: LiveData<RepositoryModel> get() = repositoryMutableLiveData

    fun getRepositoriesForUser(user: String) =  viewModelScope.launch {
        repositoriesMutableLiveData.value = repository.getRepositoriesForUser(user)
    }

    fun getRepository(owner: String, repo: String) =  viewModelScope.launch {
        repositoryMutableLiveData.value = repository.getRepository(owner, repo)
    }
}