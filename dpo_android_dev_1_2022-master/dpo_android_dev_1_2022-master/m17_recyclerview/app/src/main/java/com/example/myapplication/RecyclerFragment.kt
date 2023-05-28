package com.example.myapplication

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope

import androidx.navigation.fragment.findNavController
import com.example.myapplication.data.data_classes.PhotoDto
import com.example.myapplication.databinding.FragmentRecyclerBinding
import com.example.myapplication.presentation.MainViewModel

import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [RecyclerFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
@AndroidEntryPoint
class RecyclerFragment : Fragment() {
    lateinit var binding: FragmentRecyclerBinding
    private val viewModel: MainViewModel by viewModels()

    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater , container: ViewGroup? ,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRecyclerBinding.inflate(layoutInflater)
        // Inflate the layout for this fragment
        binding.bRefresh.setOnClickListener {
            lifecycleScope.launch {
                viewModel.reloadPhotos()
            }
        }
        lifecycleScope.launchWhenStarted {
            viewModel.photos.collect { photos ->
                if (photos != null) {
                    val adapter = CustomAdapter(photos) { photo -> onItemClick(photo) }
                    binding.rvMain.adapter = adapter

                }
            }
        }
        return binding.root
    }

    private fun onItemClick(item: PhotoDto) {
        val bundle = bundleOf(
            PHOTO_KEY to item.imgSrc
        )
        findNavController().navigate(R.id.action_recyclerFragment_to_photoFragment , bundle)
        Log.d("MyLog" , "${item.imgSrc.toString()}")
    }

    companion object {
        const val PHOTO_KEY: String = "key_photo"

        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment RecyclerFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String , param2: String) =
            RecyclerFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1 , param1)
                    putString(ARG_PARAM2 , param2)
                }
            }
    }

}