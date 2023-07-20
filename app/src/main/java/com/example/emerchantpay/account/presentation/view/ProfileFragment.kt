
import android.os.Bundle
import androidx.navigation.fragment.findNavController
import com.example.emerchantpay.account.presentation.view.BaseAccountFragment
import com.example.emerchantpay.common.constants.NavigationConstants
import com.example.emerchantpaytest.R

class ProfileFragment : BaseAccountFragment() {

    override fun setClickListeners() {
        binding.btnRepositories.setOnClickListener {
            val bundle = Bundle()
            bundle.putString("username", user.login)
            bundle.putBoolean("isAuthenticated", true)
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
}