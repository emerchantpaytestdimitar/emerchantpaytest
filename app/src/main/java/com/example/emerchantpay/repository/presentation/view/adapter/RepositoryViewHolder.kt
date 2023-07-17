package com.example.emerchantpay.repository.presentation.view.adapter

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.emerchantpay.repository.domain.RepositoryModel
import com.example.emerchantpaytest.R

class RepositoryViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val tvName: TextView = view.findViewById(R.id.tvName)
    private val tvId: TextView = view.findViewById(R.id.tvId)

    fun bind(repository: RepositoryModel) {
        tvName.text = repository.name
        tvId.text = repository.id.toString()
    }
}