import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.CookieManager
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.emerchantpay.account.presentation.viewmodel.AccountViewModel
import com.example.emerchantpay.data.di.AUTHENTICATION_URL
import com.example.emerchantpay.data.di.REDIRECT_URL
import com.example.emerchantpay.data.remote.CLIENT_ID
import com.example.emerchantpaytest.R
import com.example.emerchantpaytest.databinding.FragmentLoginBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginFragment : Fragment() {

    lateinit var binding: FragmentLoginBinding
    private val viewModel: AccountViewModel by viewModel()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setupTokenObserving()
        setupLoginObserving()
        setupWebView()
    }

    private fun setupWebView() {
        val url = Uri.parse(AUTHENTICATION_URL).buildUpon()
            .appendQueryParameter("client_id", CLIENT_ID)
            .build()
            .toString()

        val ws: WebSettings = binding.webView.settings
        ws.saveFormData = false
        ws.savePassword = false
        ws.domStorageEnabled = false
        ws.javaScriptEnabled = true
        ws.cacheMode = WebSettings.LOAD_NO_CACHE

        CookieManager.getInstance().removeAllCookies(null);
        CookieManager.getInstance().flush();

        val webClient = object : WebViewClient() {

            override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
                if (url != null && url.startsWith(REDIRECT_URL)) {
                    val code = Uri.parse(url).getQueryParameter("code")
                    if (code != null) {
                        viewModel.getToken(code)
                    }
                    return true
                }
                return false
            }
        }
        binding.webView.webViewClient = webClient
        binding.webView.loadUrl(url)
    }

    private fun setupTokenObserving() {
        viewModel.accessTokenLiveData.observe(viewLifecycleOwner) { accessTokenModel ->
            viewModel.performLogin(accessTokenModel)
        }
    }

    private fun setupLoginObserving() {
        viewModel.userLiveData.observe(viewLifecycleOwner) { user ->
            findNavController().navigate(R.id.profileFragment)
        }
    }

}