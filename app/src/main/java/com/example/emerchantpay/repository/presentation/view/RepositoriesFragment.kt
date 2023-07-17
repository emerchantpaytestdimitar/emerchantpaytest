import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.emerchantpay.repository.presentation.view.adapter.RepositoryAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel
import com.example.emerchantpay.repository.presentation.viewmodel.RepositoryViewModel
import com.example.emerchantpaytest.databinding.FragmentRepositoriesBinding
import com.example.emerchantpaytest.databinding.FragmentRepositoryBinding

class RepositoriesFragment : Fragment() {

    private val viewModel: RepositoryViewModel by viewModel()
    private lateinit var adapter: RepositoryAdapter
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
        val username: String? = arguments?.getString("username")
        username?.let {
            viewModel.getRepositoriesForUser(it)
        }
    }

    private fun setupRepositoriesObserving() {
        viewModel.repositoriesLiveData.observe(viewLifecycleOwner) { repositories ->
            adapter = RepositoryAdapter(repositories)
            binding.recyclerView.adapter = adapter
        }
    }
}