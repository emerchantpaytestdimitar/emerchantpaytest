import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.example.emerchantpay.account.domain.model.User
import com.example.emerchantpay.common.constants.NavigationConstants
import com.example.emerchantpaytest.R
import com.example.emerchantpaytest.databinding.FragmentProfileBinding

class ProfileFragment : Fragment() {

    private lateinit var binding: FragmentProfileBinding
    private lateinit var ownerName: String
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
            bundle.putString("token", token)
            findNavController().navigate(R.id.userListFragment, bundle)
        }

        binding.following.setOnClickListener {
            val bundle = Bundle()
            bundle.putString(
                NavigationConstants.NAVIGATION_CONSTANT_KEY,
                NavigationConstants.NAVIGATION_CONSTANT_FOLLOWING
            )
            bundle.putString("ownerName", user.login)
            bundle.putString("token", token)
            findNavController().navigate(R.id.userListFragment, bundle)
        }
    }

}