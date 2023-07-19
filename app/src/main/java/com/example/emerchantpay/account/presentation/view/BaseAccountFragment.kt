package com.example.emerchantpay.account.presentation.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.example.emerchantpay.account.domain.model.User
import com.example.emerchantpay.common.SecureTokenStorageUtil
import com.example.emerchantpay.common.constants.NavigationConstants
import com.example.emerchantpaytest.R
import com.example.emerchantpaytest.databinding.FragmentProfileBinding

abstract class BaseAccountFragment: Fragment() {

    private lateinit var binding: FragmentProfileBinding
    private lateinit var user: User
    private lateinit var token: String

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupMenu()
        disableBackNavigation()
        val user: User? = arguments?.getParcelable("user")
        user?.let {
            this.user = it
            binding.username.text = it.login
            binding.followersNumber.text = it.followers.toString()
            binding.followingNumber.text = it.following.toString()

            val options = RequestOptions()
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .skipMemoryCache(true)

            Glide.with(this)
                .load(it.avatarUrl)
                .override(300, 300)
                .apply(options)
                .into(binding.avatarImage)
        }

        arguments?.getString("token")?.let {
            this.token = it
        }
        setClickListeners()
    }

    private fun setClickListeners() {

        binding.btnRepositories.setOnClickListener {
            val bundle = Bundle()
            bundle.putString("username", user?.login)
            findNavController().navigate(R.id.repositoryFragment, bundle)
        }

        binding.followers.setOnClickListener {
            val bundle = Bundle()
            bundle.putString(
                NavigationConstants.NAVIGATION_CONSTANT_KEY,
                NavigationConstants.NAVIGATION_CONSTANT_FOLLOWERS
            )
            bundle.putString("ownerName", user.login)
            findNavController().navigate(R.id.userListFragment, bundle)
        }

        binding.following.setOnClickListener {
            val bundle = Bundle()
            bundle.putString(
                NavigationConstants.NAVIGATION_CONSTANT_KEY,
                NavigationConstants.NAVIGATION_CONSTANT_FOLLOWING
            )
            bundle.putString("ownerName", user.login)
            findNavController().navigate(R.id.userListFragment, bundle)
        }
    }

    private fun setupMenu() {
        val menuHost: MenuHost = requireActivity()
        menuHost.addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.menu_profile, menu)
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                return when (menuItem.itemId) {
                    R.id.logout -> {
                        SecureTokenStorageUtil.deleteToken(requireContext())
                        findNavController().navigate(R.id.action_logout)
                        true
                    }

                    else -> false
                }
            }
        }, viewLifecycleOwner, Lifecycle.State.RESUMED)
    }

    private fun disableBackNavigation() {
        val callback: OnBackPressedCallback =
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    isEnabled = false
                }
            }
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, callback)
    }
}