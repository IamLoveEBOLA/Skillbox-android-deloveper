package com.example.myapplication.presentation.ui.search_history_screen

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.myapplication.App
import com.example.myapplication.data.models.saved_city.SavedCity
import com.example.myapplication.databinding.FragmentSearchHistoryBinding
import com.example.myapplication.domain.utils.State
import com.example.myapplication.presentation.ui.search_history_screen.adapter.SavedCityListAdapter
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.launch

class CitySearchHistoryFragment : Fragment() {

    private var _binding: FragmentSearchHistoryBinding? = null
    private val binding get() = _binding!!
    private val citySearchHistoryViewModel
            by viewModels<CitySearchHistoryViewModel> {
                App.appComponent.citySearchHistoryViewModelFactory()
            }

    private val adapter = SavedCityListAdapter { item -> onItemClick(item) }


    override fun onCreateView(
        inflater: LayoutInflater , container: ViewGroup? , savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSearchHistoryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View , savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.recyclerView.adapter = adapter

        binding.buttonClear.setOnClickListener {
            citySearchHistoryViewModel.clearHistory()
            citySearchHistoryViewModel.loadCityList()
        }

        viewLifecycleOwner.lifecycleScope.launch {
            citySearchHistoryViewModel.state.collect {
                when (it) {

                    State.Success -> Snackbar.make(
                        binding.recyclerView,
                        "Load from DB success",
                        Snackbar.LENGTH_SHORT
                    ).show()

                    State.Loading -> visibilityLoading()

                    is State.Error -> Snackbar.make(
                        binding.recyclerView,
                        it.error,
                        Snackbar.LENGTH_SHORT
                    ).show()
                }
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            citySearchHistoryViewModel.cityList.collect {
                adapter.submitList(it)
            }
        }

    }

    private fun visibilityLoading() {
        Snackbar.make(binding.recyclerView, "Начинаю загружать историю поиска", Snackbar.LENGTH_SHORT)
            .show()
    }

    private fun onItemClick(it: SavedCity) {
        Log.d("TAG", "onItemClick: clicked: $it")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}