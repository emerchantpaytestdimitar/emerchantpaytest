package com.example.emerchantpay.repository.presentation.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.emerchantpay.repository.domain.RepositoryModel
import com.example.emerchantpaytest.R

class RepositoryAdapter(private val repositoryList: List<RepositoryModel>) : RecyclerView.Adapter<RepositoryViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepositoryViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_repository, parent, false)
        return RepositoryViewHolder(view)
    }

    override fun onBindViewHolder(holder: RepositoryViewHolder, position: Int) {
        holder.bind(repositoryList[position])
    }

    override fun getItemCount() = repositoryList.size
}