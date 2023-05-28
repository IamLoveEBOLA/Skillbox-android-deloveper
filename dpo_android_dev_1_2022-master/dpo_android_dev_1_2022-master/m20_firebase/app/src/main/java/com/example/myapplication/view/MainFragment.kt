package com.example.myapplication.view

import android.Manifest
import android.annotation.SuppressLint
import android.app.PendingIntent
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.BitmapFactory
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.myapplication.App
import com.example.myapplication.MainActivity
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
                Toast.makeText(context , R.string.error_no_is_granted , Toast.LENGTH_LONG)
                    .show()
            }

        }

    private val launcherMaps =
        registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { map ->
            if (map.values.all { it }) {
                findNavController().navigate(R.id.action_mainFragment_to_mapsFragment)
            } else {
                Toast.makeText(context , R.string.error_no_is_granted , Toast.LENGTH_LONG)
                    .show()
            }

        }

    override fun onCreateView(
        inflater: LayoutInflater , container: ViewGroup? ,
        savedInstanceState: Bundle?
    ): View? {
        /* binding.crashButton.setOnClickListener {
             throw RuntimeException("Test Crash")
         }*/

        binding = FragmentMainBinding.inflate(layoutInflater)
        binding.bPhoto.setOnClickListener { checkCameraPermission() }
        binding.bMap.setOnClickListener {
            checkMapPermission()
            createNotification()
        }
        binding.button123.setOnClickListener { createNotification() }
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


    @SuppressLint("MissingPermission")
    private fun createNotification() {
        val intent = Intent(requireContext() , MainActivity::class.java)
        val pendingIntent = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S)
            PendingIntent.getActivity(requireContext() , 0 , intent , PendingIntent.FLAG_IMMUTABLE)
        else
            PendingIntent.getActivity(
                requireContext() ,
                0 ,
                intent ,
                PendingIntent.FLAG_UPDATE_CURRENT
            )
        val icon = R.drawable.ic_notifacation
        val notification = NotificationCompat.Builder(requireContext() , App.CHANNEL_ID)
            .setSmallIcon(icon)
            .setContentTitle("Test")
            .setContentText(
                "Будь как дома, путник " +
                        "Я ни в чём не откажу " +
                        "Я ни в чём не откажу " +
                        "Я ни в чём не откажу!"
            )
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)
            .build()

        NotificationManagerCompat.from(requireContext()).notify(NOTIFICATION_ID , notification)
    }

    private fun openMap() {
        findNavController().navigate(R.id.action_mainFragment_to_mapsFragment)
    }

    private fun onItemClick(item: Photo) {
        val bundle = bundleOf(
            PHOTO_KEY to item.uri
        )
        findNavController().navigate(R.id.action_mainFragment_to_photoFragment , bundle)
    }

    private fun checkMapPermission() {
        val isAllGranted = REQUEST_PERMISSIONS_MAP.all { permission ->
            context?.let {
                ContextCompat.checkSelfPermission(
                    it ,
                    permission
                )
            } == PackageManager.PERMISSION_GRANTED
        }

        if (isAllGranted) {
            Toast.makeText(context , R.string.granted_ok , Toast.LENGTH_LONG).show()
            findNavController().navigate(R.id.action_mainFragment_to_mapsFragment)
        } else {
            launcherMaps.launch(REQUEST_PERMISSIONS_MAP)
        }
    }

    private fun checkCameraPermission() {
        val isAllGranted = REQUEST_PERMISSIONS.all { permission ->
            context?.let {
                ContextCompat.checkSelfPermission(
                    it ,
                    permission
                )
            } == PackageManager.PERMISSION_GRANTED
        }
        if (isAllGranted) {
            Toast.makeText(context , R.string.granted_ok , Toast.LENGTH_LONG).show()
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

        private val REQUEST_PERMISSIONS_MAP: Array<String> = buildList {
            add(Manifest.permission.ACCESS_COARSE_LOCATION)
            add(Manifest.permission.ACCESS_FINE_LOCATION)
        }.toTypedArray()

        private const val NOTIFICATION_ID = 1000
    }
}
