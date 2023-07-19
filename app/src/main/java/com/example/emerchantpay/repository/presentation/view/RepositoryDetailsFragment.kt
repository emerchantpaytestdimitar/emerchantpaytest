package com.example.emerchantpay.repository.presentation.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.emerchantpay.common.constants.NavigationConstants
import com.example.emerchantpay.repository.presentation.viewmodel.RepositoryViewModel
import com.example.emerchantpaytest.R
import com.example.emerchantpaytest.databinding.FragmentRepositoryBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class RepositoryDetailsFragment : Fragment() {

    private val viewModel: RepositoryViewModel by viewModel()
    private lateinit var binding: FragmentRepositoryBinding

    private lateinit var repoName: String
    private lateinit var ownerName: String

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
            this.ownerName = ownerString
            arguments?.getString("repo")?.let { repo ->
                this.repoName = repo
                viewModel.getRepository(owner, repo)
                setClickListeners()
            }
        }
    }

    private fun setupRepositoriesObserving() {
        viewModel.repositoryLiveData.observe(viewLifecycleOwner) { repository ->
            binding.tvRepositoryName.text = repository.name
            binding.tvOwner.text = repository.owner.login
        }
    }

    private fun setClickListeners() {
        binding.tvContributors.setOnClickListener {
            val bundle = Bundle()
            bundle.putString(
                NavigationConstants.NAVIGATION_CONSTANT_KEY,
                NavigationConstants.NAVIGATION_CONSTANT_CONTRIBUTORS
            )
            bundle.putString("repoName", getString("repo"))
            bundle.putString("ownerName", getString("owner"))
            findNavController().navigate(R.id.userListFragment, bundle)
        }
    }

    private fun getString(key: String): String {
        arguments?.getString(key)?.let {
            return it
        }
        return ""
    }
}