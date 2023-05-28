package myapplication.Main.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.myapplication.databinding.FragmentMainBinding
import myapplication.Main.di.DaggerAppComponent
import myapplication.Main.presentation.MainViewModel


const val KEY_RESULT_TEXT = "ResultText"

class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    private val viewModel: MainViewModel by viewModels {
        DaggerAppComponent.create().mainViewModelFactory()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // TODO: Use the ViewModel
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentMainBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.Search.setOnClickListener {
            savedInstanceState?.let { bundle ->
                binding.message.text = bundle.getString(KEY_RESULT_TEXT)
            }
            viewLifecycleOwner.lifecycleScope.launchWhenStarted {
                viewModel.reloadUsefulActivity().collect({ text ->
                    binding.message.text = text.activity
                })
            }
        }
    }
}