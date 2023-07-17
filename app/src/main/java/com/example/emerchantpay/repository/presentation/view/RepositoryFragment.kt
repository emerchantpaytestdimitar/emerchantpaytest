import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.example.emerchantpay.repository.presentation.view.adapter.RepositoryAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel
import com.example.emerchantpay.repository.presentation.viewmodel.RepositoryViewModel
import com.example.emerchantpaytest.R
import com.example.emerchantpaytest.databinding.FragmentRepositoryBinding

class RepositoryFragment : Fragment() {

    private val viewModel: RepositoryViewModel by viewModel()
    private lateinit var adapter: RepositoryAdapter
    private lateinit var binding: FragmentRepositoryBinding

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
        val username: String? = arguments?.getString("username")
        username?.let {
            viewModel.getRepositoriesForUser(it)
        }
    }

    private fun setupRepositoriesObserving() {
        viewModel.repositoriesLiveData.observe(viewLifecycleOwner) { repositories ->
            Log.i("", "repositories : $repositories")
            adapter = RepositoryAdapter(repositories)
            binding.recyclerView.adapter = adapter
            adapter.notifyDataSetChanged()
        }
    }
}