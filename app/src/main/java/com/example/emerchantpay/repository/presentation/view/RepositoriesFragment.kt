import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import com.example.emerchantpay.account.domain.model.User
import com.example.emerchantpay.common.SecureTokenStorageUtil
import com.example.emerchantpay.repository.domain.model.RepositoryModel
import com.example.emerchantpay.repository.presentation.view.adapter.RepositoryAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel
import com.example.emerchantpay.repository.presentation.viewmodel.RepositoryViewModel
import com.example.emerchantpaytest.databinding.FragmentRepositoriesBinding

class RepositoriesFragment : Fragment() {

    private val viewModel: RepositoryViewModel by viewModel()
    private lateinit var repositoriesAdapter: RepositoryAdapter
    private lateinit var binding: FragmentRepositoriesBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRepositoriesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setupRepositoriesObserving()
        setupSearch()
        val username: User? = arguments?.getParcelable("user")
        username?.let { user ->
            arguments?.getBoolean("isAuthenticated")?.let { isAuthenticated ->
                if (isAuthenticated) {
                    SecureTokenStorageUtil.retrieveToken(requireContext())?.let { token ->
                        viewModel.getStarredRepositoriesForAuthenticatedUser(
                            user = user,
                            token = token
                        )
                    }
                } else {
                    viewModel.getRepositoriesForUnAuthenticatedUser(user)
                }
            }
        }
    }

    private fun setupSearch() {
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                SecureTokenStorageUtil.retrieveToken(requireContext())?.let { token ->
                    viewModel.searchRepositories(query = query, token = token)
                }
                return true
            }

            override fun onQueryTextChange(newText: String): Boolean {
                return false
            }
        })
    }

    private fun setupRepositoriesObserving() {
        viewModel.repositoriesLiveData.observe(viewLifecycleOwner) { repositories ->
            loadRepositoriesIntoAdapter(repositories)
        }
        viewModel.searchRepositoriesLiveData.observe(viewLifecycleOwner) { repositories ->
            loadRepositoriesIntoAdapter(repositories)
        }
    }

    private fun loadRepositoriesIntoAdapter(repositories: List<RepositoryModel>) {
        if (repositories.isEmpty()) {
            displayEmptyDataToast()
        }
        repositoriesAdapter = RepositoryAdapter(repositories)
        binding.recyclerView.apply {
            adapter = repositoriesAdapter
        }
        repositoriesAdapter.notifyDataSetChanged()
    }

    private fun displayEmptyDataToast() {
        Toast.makeText(requireContext(), "No Repositories to Show", Toast.LENGTH_LONG).show()
    }
}