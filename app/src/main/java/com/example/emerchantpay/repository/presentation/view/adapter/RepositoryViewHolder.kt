package com.example.emerchantpay.repository.presentation.view.adapter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.emerchantpay.repository.domain.model.RepositoryModel
import com.example.emerchantpaytest.R
import com.example.emerchantpaytest.databinding.ItemRepositoryBinding

class RepositoryViewHolder(private val binding: ItemRepositoryBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(repository: RepositoryModel) {
        binding.tvName.text = repository.name
        binding.tvId.text = repository.id.toString()

        binding.root.setOnClickListener {
            val bundle: Bundle = Bundle()
            bundle.putString("owner", repository.owner.login)
            bundle.putString("repo", repository.name)
            it.findNavController().navigate(R.id.repositoryDetailsFragment, bundle)
        }
    }

    companion object {
        fun from(parent: ViewGroup): RepositoryViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding = ItemRepositoryBinding.inflate(layoutInflater, parent, false)
            return RepositoryViewHolder(binding)
        }
    }
}