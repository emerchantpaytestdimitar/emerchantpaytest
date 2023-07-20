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
import com.example.emerchantpay.account.presentation.viewmodel.AccountViewModel
import com.example.emerchantpay.common.SecureTokenStorageUtil
import com.example.emerchantpay.common.constants.NavigationConstants
import com.example.emerchantpaytest.R
import com.example.emerchantpaytest.databinding.FragmentProfileBinding
import org.koin.androidx.viewmodel.ext.android.viewModel


abstract class BaseAccountFragment : Fragment() {

    private val viewModel: AccountViewModel by viewModel()

    protected lateinit var binding: FragmentProfileBinding
    protected lateinit var user: User

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
        setupObserving()
        disableBackNavigation()
        setClickListeners()
        val user: User? = arguments?.getParcelable("user")
        user?.let {
            SecureTokenStorageUtil.retrieveToken(requireContext())?.let { token ->
                viewModel.getUser(userId = user.login, token = token)
            }
        }
    }

    protected abstract fun setClickListeners()

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

    private fun setupObserving() {
        viewModel.getUserLiveData.observe(viewLifecycleOwner) { user ->
            this.user = user
            binding.username.text = user.login
            binding.followersNumber.text = user.followers.toString()
            binding.followingNumber.text = user.following.toString()

            val options = RequestOptions()
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .skipMemoryCache(true)

            Glide.with(this)
                .load(user.avatarUrl)
                .override(300, 300)
                .apply(options)
                .into(binding.avatarImage)
        }
    }
}