package com.example.emerchantpay.account.presentation.view

import android.os.Bundle
import androidx.navigation.fragment.findNavController
import com.example.emerchantpay.common.constants.NavigationConstants
import com.example.emerchantpaytest.R

class UserFragment : BaseAccountFragment() {

    override fun setClickListeners() {
        binding.btnRepositories.setOnClickListener {
            val bundle = Bundle()
            bundle.putParcelable("user", user)
            bundle.putBoolean("isAuthenticated", false)
            findNavController().navigate(R.id.repositoryFragment, bundle)
        }

        binding.followers.setOnClickListener {
            val bundle = Bundle()
            bundle.putString(
                NavigationConstants.NAVIGATION_CONSTANT_KEY,
                NavigationConstants.NAVIGATION_CONSTANT_FOLLOWERS
            )
            bundle.putParcelable("user", user)
            findNavController().navigate(R.id.userListFragment, bundle)
        }

        binding.following.setOnClickListener {
            val bundle = Bundle()
            bundle.putString(
                NavigationConstants.NAVIGATION_CONSTANT_KEY,
                NavigationConstants.NAVIGATION_CONSTANT_FOLLOWING
            )
            bundle.putParcelable("user", user)
            findNavController().navigate(R.id.userListFragment, bundle)
        }
    }
}