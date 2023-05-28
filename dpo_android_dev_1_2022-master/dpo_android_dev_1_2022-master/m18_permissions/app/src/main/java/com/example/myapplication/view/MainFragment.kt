package com.example.myapplication.view

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.myapplication.R
import com.example.myapplication.data.Photo
import com.example.myapplication.databinding.FragmentMainBinding
import com.example.myapplication.presentation.MainViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainFragment : Fragment() {
    lateinit var binding: FragmentMainBinding
    private val viewModel: MainViewModel by viewModels()

    private val launcher =
        registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { map ->
            if (map.values.all { it }) {
                findNavController().navigate(R.id.action_mainFragment_to_cameraFragment)
            } else {
                Toast.makeText(context , R.string.error_no_is_granted , Toast.LENGTH_SHORT)
                    .show()
            }
        }

    override fun onCreateView(
        inflater: LayoutInflater , container: ViewGroup? ,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMainBinding.inflate(layoutInflater)
        binding.bPhoto.setOnClickListener { checkPermission() }
        lifecycleScope.launchWhenCreated {
            viewModel.allPhotos.collect { photos ->
                if (photos != null) {
                    val adapter = CustomAdapter(photos) { photo -> onItemClick(photo) }
                    binding.rvMain.adapter = adapter
                }
            }
        }
        return binding.root

    }

    private fun onItemClick(item: Photo) {
        val bundle = bundleOf(
            PHOTO_KEY to item.uri
        )
        findNavController().navigate(R.id.action_mainFragment_to_photoFragment , bundle)
    }


    private fun checkPermission() {
        val isAllGranted = REQUEST_PERMISSIONS.all { permission ->
            context?.let {
                ContextCompat.checkSelfPermission(
                    it ,
                    Manifest.permission.CAMERA
                )
            } == PackageManager.PERMISSION_GRANTED
        }
        if (isAllGranted) {
            Toast.makeText(context , R.string.granted_ok , Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_mainFragment_to_cameraFragment)
        } else {
            launcher.launch(REQUEST_PERMISSIONS)
        }
    }

    companion object {
        const val PHOTO_KEY: String = "key_photo"
        private val REQUEST_PERMISSIONS: Array<String> = buildList {
            add(Manifest.permission.CAMERA)
            if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.P) {
                add(Manifest.permission.WRITE_EXTERNAL_STORAGE)
            }
        }.toTypedArray()
    }
}
