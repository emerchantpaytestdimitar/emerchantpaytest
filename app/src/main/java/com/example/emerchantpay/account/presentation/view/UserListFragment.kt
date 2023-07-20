package com.example.emerchantpay.account.presentation.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import com.example.emerchantpay.account.domain.model.User
import com.example.emerchantpay.account.presentation.viewmodel.AccountViewModel
import com.example.emerchantpay.common.SecureTokenStorageUtil
import com.example.emerchantpay.common.constants.NavigationConstants
import com.example.emerchantpay.account.presentation.view.adapter.UsersAdapter
import com.example.emerchantpay.repository.presentation.viewmodel.RepositoryViewModel
import com.example.emerchantpaytest.databinding.FragmentUserListBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class UserListFragment : Fragment() {

    private val viewModel: AccountViewModel by viewModel()
    private val repositoryViewModel: RepositoryViewModel by viewModel()

    private lateinit var usersAdapter: UsersAdapter
    private lateinit var binding: FragmentUserListBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentUserListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setupLiveDataObserving()
        initDataLoading()
        setupSearch()
    }

    private fun initDataLoading() {
        arguments?.getString(NavigationConstants.NAVIGATION_CONSTANT_KEY)?.let {
            when (it) {
                NavigationConstants.NAVIGATION_CONSTANT_FOLLOWERS -> {
                    arguments?.getParcelable<User>("user")?.let { user ->
                        SecureTokenStorageUtil.retrieveToken(requireContext())?.let { token ->
                            viewModel.listFollowers(user = user, token = token)
                        }
                    }
                }

                NavigationConstants.NAVIGATION_CONSTANT_CONTRIBUTORS -> {
                    arguments?.getString("repoName")?.let { repo ->
                        arguments?.getString("ownerName")?.let { ownerName ->
                            repositoryViewModel.listRepoContributors(owner = ownerName, repo = repo)
                        }
                    }
                }

                NavigationConstants.NAVIGATION_CONSTANT_FOLLOWING -> {
                    arguments?.getParcelable<User>("user")?.let { user ->

                        SecureTokenStorageUtil.retrieveToken(requireContext())?.let { token ->
                            viewModel.listFollowing(user = user, token = token)
                        }
                    }
                }

                else -> {}
            }
        }
    }

    private fun setupSearch() {
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                SecureTokenStorageUtil.retrieveToken(requireContext())?.let { token ->
                    viewModel.searchUsers(query = query, token = token)
                }
                return true
            }

            override fun onQueryTextChange(newText: String): Boolean {
                return false
            }
        })
    }

    private fun setupLiveDataObserving() {
        repositoryViewModel.contributorsLiveData.observe(viewLifecycleOwner) { contributors ->
            contributors?.let {
                loadUsersIntoAdapter(it)
            }
        }

        viewModel.listFollowersLiveData.observe(viewLifecycleOwner) { followers ->
            loadUsersIntoAdapter(followers)
        }

        viewModel.listFollowingsLiveData.observe(viewLifecycleOwner) { followers ->
            loadUsersIntoAdapter(followers)
        }

        viewModel.searchUsersLiveData.observe(viewLifecycleOwner) { searchedUsers ->
            loadUsersIntoAdapter(searchedUsers)
        }
    }

    private fun loadUsersIntoAdapter(users: List<User?>) {
        if (users.isEmpty()) {
            displayEmptyDataToast()
        }
        usersAdapter = UsersAdapter(users)
        binding.recyclerView.apply {
            adapter = usersAdapter
        }
        usersAdapter.notifyDataSetChanged()
    }

    private fun displayEmptyDataToast() {
        Toast.makeText(requireContext(), "No Users to Show", Toast.LENGTH_LONG).show()
    }
}