package com.example.emerchantpay.repository.presentation.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.emerchantpay.repository.presentation.viewmodel.RepositoryViewModel
import com.example.emerchantpaytest.databinding.FragmentRepositoryBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class RepositoryDetailsFragment : Fragment() {

    private val viewModel: RepositoryViewModel by viewModel()
    private lateinit var binding: FragmentRepositoryBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRepositoryBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setupRepositoriesObserving()
        val owner: String? = arguments?.getString("owner")
        owner?.let { ownerString ->
            arguments?.getString("repo")?.let { repo ->
                viewModel.getRepository(owner, repo)
            }
        }
    }

    private fun setupRepositoriesObserving() {
        viewModel.repositoryLiveData.observe(viewLifecycleOwner) { repository ->
            binding.tvRepositoryName.text = repository.name
            binding.tvOwner.text = repository.owner.login
        }
    }
}