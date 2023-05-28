package com.example.myapplication.presentation.ui.city_search_screen

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.myapplication.App
import com.example.myapplication.data.models.city.CityListItem
import com.example.myapplication.databinding.FragmentSearchBinding
import com.example.myapplication.domain.utils.State
import com.example.myapplication.presentation.ui.city_search_screen.adapter.CityListAdapter
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.launch

class SearchFragment : Fragment() {
    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!
    private val searchFragmentViewModel by viewModels<SearchFragmentViewModel> { App.appComponent.searchViewModelFactory() }


    private val adapter = CityListAdapter { item -> onItemClick(item) }

    override fun onCreateView(
        inflater: LayoutInflater , container: ViewGroup? , savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSearchBinding.inflate(inflater , container , false)
        return binding.root
    }

    override fun onViewCreated(view: View , savedInstanceState: Bundle?) {
        super.onViewCreated(view , savedInstanceState)

        binding.recyclerView.adapter = adapter

        binding.btnSearch.setOnClickListener {
            val inputtedText = binding.search.text
            Log.d("TAG" , "Поиск: $inputtedText")
            if (inputtedText!!.length >= 3) {
                searchFragmentViewModel.loadCityList(inputtedText.toString())
            } else Snackbar.make(
                binding.search , "Ввеедите название города больше 3 букв" , Snackbar.LENGTH_SHORT
            ).show()
        }



        viewLifecycleOwner.lifecycleScope.launch {
            searchFragmentViewModel.state.collect {
                Log.d("TAG" , "STATE: $it")
                when (it) {
                    State.Success -> adapter.submitList(searchFragmentViewModel.cityList.value)
                    State.Loading -> visibilityLoading()
                    is State.Error -> Snackbar.make(
                        binding.search ,
                        "Ошибка" ,
                        Snackbar.LENGTH_SHORT
                    )
                        .show()
                }
            }
        }
    }

    private fun visibilityLoading() {
        Snackbar.make(
            binding.recyclerView ,
            "Идет поиск, пожалуйста подождите" ,
            Snackbar.LENGTH_SHORT
        )
            .show()
    }

    private fun onItemClick(it: CityListItem) {
        Log.d("TAG" , "onItemClick: clicked: $it")

        val direction = SearchFragmentDirections.actionFragmentSearchToCityFragment(it)
        findNavController().navigate(direction)

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}