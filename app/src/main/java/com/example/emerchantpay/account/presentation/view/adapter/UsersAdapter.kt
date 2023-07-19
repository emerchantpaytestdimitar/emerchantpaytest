package com.example.emerchantpay.account.presentation.view.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.emerchantpay.account.domain.model.User

class UsersAdapter(private val usersList: List<User?>) : RecyclerView.Adapter<UsersViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UsersViewHolder {
        return UsersViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: UsersViewHolder, position: Int) {
        holder.bind(usersList[position])
    }

    override fun getItemCount() = usersList.size
}