package com.example.emerchantpay.repository.presentation.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import com.example.emerchantpay.common.SecureTokenStorageUtil
import com.example.emerchantpay.common.constants.NavigationConstants
import com.example.emerchantpay.repository.domain.model.RepositoryModel
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
        setupMenu()
        setupRepositoriesObserving()
            arguments?.getParcelable<RepositoryModel>("repo")?.let { repo ->
                this.repoName = repo.name
                binding.tvRepositoryName.text = repo.name
                binding.tvOwner.text = repo.owner.login
                SecureTokenStorageUtil.retrieveToken(requireContext())?.let { token ->
                    viewModel.checkIfRepoIsStarred(owner = repo.owner.login, repo = repo.name, token = token)
                }
            }
    }

    private fun setupRepositoriesObserving() {
        viewModel.repositoryLiveData.observe(viewLifecycleOwner) { repository ->
            binding.tvRepositoryName.text = repository.name
            binding.tvOwner.text = repository.owner.login
        }

        viewModel.starRepoLiveData.observe(viewLifecycleOwner) {
            if (it) {
                Toast.makeText(requireContext(), "REPO SUCCESSFULLY STARRED", Toast.LENGTH_LONG)
                    .show()
            } else {
                Toast.makeText(requireContext(), "REPO NOT SUCCESSFULLY STARRED", Toast.LENGTH_LONG)
                    .show()
            }
        }

        viewModel.unsStarRepoLiveData.observe(viewLifecycleOwner) {
            if (it) {
                Toast.makeText(requireContext(), "REPO SUCCESSFULLY UNSTARRED", Toast.LENGTH_LONG)
                    .show()
            } else {
                Toast.makeText(
                    requireContext(),
                    "REPO NOT SUCCESSFULLY UNSTARRED",
                    Toast.LENGTH_LONG
                ).show()
            }
        }

        viewModel.checkIfRepoIsStarredLiveData.observe(viewLifecycleOwner) {
            binding.tbStar.isChecked = it
            setClickListeners()
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

        binding.tbStar.setOnCheckedChangeListener { _, isChecked ->
            SecureTokenStorageUtil.retrieveToken(requireContext())?.let { token ->
                if (isChecked) {
                    viewModel.starRepo(
                        repo = getString("repo"),
                        owner = getString("owner"),
                        token = token
                    )
                } else {
                    viewModel.unStarRepo(
                        repo = getString("repo"),
                        owner = getString("owner"),
                        token = token
                    )
                }
            }
        }
    }

    private fun getString(key: String): String {
        val repo: RepositoryModel? = arguments?.getParcelable("repo")
        repo?.let {
            when(key) {
                "repo" -> return  repo.name
                "owner" -> return repo.owner.login
                else -> {return ""}
            }
        }
        return ""
    }

    private fun setupMenu() {
        val menuHost: MenuHost = requireActivity()
        menuHost.addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.menu_repository, menu)
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                return when (menuItem.itemId) {
                    R.id.logout -> {
                        SecureTokenStorageUtil.deleteToken(requireContext())
                        findNavController().navigate(R.id.action_logout)
                        true
                    }

                    R.id.profile -> {
                        findNavController().popBackStack(R.id.profileFragment, false)
                        true
                    }

                    else -> false
                }
            }
        }, viewLifecycleOwner, Lifecycle.State.RESUMED)
    }
}