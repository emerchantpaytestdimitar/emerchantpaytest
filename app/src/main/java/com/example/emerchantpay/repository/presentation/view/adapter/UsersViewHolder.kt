package com.example.emerchantpay.repository.presentation.view.adapter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.emerchantpay.account.domain.model.User
import com.example.emerchantpay.repository.domain.model.RepositoryModel
import com.example.emerchantpaytest.R
import com.example.emerchantpaytest.databinding.ItemRepositoryBinding

class UsersViewHolder (private val binding: ItemRepositoryBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(user: User) {
        binding.tvName.text = user.login
        binding.tvId.text = user.id.toString()

        binding.root.setOnClickListener {
            val bundle = Bundle()
            bundle.putParcelable("user", user)
            it.findNavController().navigate(R.id.profileFragment, bundle)
        }
    }

    companion object {
        fun from(parent: ViewGroup): UsersViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding = ItemRepositoryBinding.inflate(layoutInflater, parent, false)
            return UsersViewHolder(binding)
        }
    }
}