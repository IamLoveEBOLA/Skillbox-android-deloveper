package com.example.myapplication.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.example.myapplication.R
import com.example.myapplication.databinding.DialogPlaceInfoBinding
import com.example.myapplication.databinding.FragmentMapsBinding
import com.example.myapplication.presentation.MapsViewModel
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.LatLngBounds
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.material.bottomsheet.BottomSheetDialog
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MapsFragment : Fragment() {
    lateinit var binding: FragmentMapsBinding
    private val viewModel: MapsViewModel by viewModels()
    private lateinit var googleMap1: GoogleMap
    private var savedCameraPosition: CameraPosition? = null
    private var savedZoomLevel: Float? = null
    private lateinit var bottomSheetDialog: BottomSheetDialog
    private lateinit var dialogBinding: DialogPlaceInfoBinding
    private val callback = OnMapReadyCallback { googleMap ->
        with(googleMap.uiSettings) {
            isZoomControlsEnabled = true
            isMyLocationButtonEnabled = true
        }
        googleMap.isMyLocationEnabled = true
        googleMap1 = googleMap
        if (savedCameraPosition != null && savedZoomLevel != null) {
            googleMap.moveCamera(CameraUpdateFactory.newCameraPosition(savedCameraPosition!!))
            googleMap.moveCamera(CameraUpdateFactory.zoomTo(savedZoomLevel!!))
        }


        var fusedLocationClient =
            context?.let { LocationServices.getFusedLocationProviderClient(it) }
        var location = fusedLocationClient?.lastLocation
        if (location != null) {
            location.addOnCompleteListener {
                if (it.isSuccessful) {
                    val myLoc = LatLng(location.result!!.latitude , location.result.longitude)
                    googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(myLoc , 15f))
                }
            }
        }
        googleMap.setOnCameraIdleListener {
            var a = googleMap.cameraPosition.target
            val bounds: LatLngBounds = googleMap.projection.visibleRegion.latLngBounds
            viewModel.buttonOnClick(
                lon_min = bounds.southwest.longitude ,
                lat_min = bounds.southwest.latitude ,
                lon_max = bounds.northeast.longitude ,
                lat_max = bounds.northeast.latitude
            )
        }
        googleMap.setOnMarkerClickListener { marker ->
            if (marker.isInfoWindowShown) {
                marker.hideInfoWindow()
            } else {
                marker.showInfoWindow()
            }
            marker.snippet?.let {
                viewModel.markerOnClick(it)
                bottomSheetDialog.show()
            }
            true
        }
        googleMap1 = googleMap
    }


    override fun onCreateView(
        inflater: LayoutInflater ,
        container: ViewGroup? ,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMapsBinding.inflate(layoutInflater)

        bottomSheetDialog = BottomSheetDialog(this.requireContext())
        dialogBinding = DialogPlaceInfoBinding.inflate(layoutInflater)
        bottomSheetDialog.setContentView(dialogBinding.root)
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(object : OnMapReadyCallback {
            override fun onMapReady(googleMap: GoogleMap) {
                with(googleMap.uiSettings) {
                    isZoomControlsEnabled = true
                    isMyLocationButtonEnabled = true
                }
                googleMap.isMyLocationEnabled = true

                googleMap1 = googleMap
                if (savedCameraPosition != null && savedZoomLevel != null) {
                    googleMap.moveCamera(CameraUpdateFactory.newCameraPosition(savedCameraPosition!!))
                    googleMap.moveCamera(CameraUpdateFactory.zoomTo(savedZoomLevel!!))
                }

            }

        })
        lifecycleScope.launchWhenStarted {
            viewModel.places.collect { places ->
                Log.d("MyLog" , "maps fragment${places.toString()}")
                if (::googleMap1.isInitialized) {
                    googleMap1.clear()
                    places?.forEach { place ->
                        val pos = LatLng(place.point?.lat!! , place.point?.lon!!)
                        googleMap1.addMarker(
                            MarkerOptions()
                                .position(pos)
                                .snippet(place.xid)
                                .title("${place.name}")
                        )
                        var a = googleMap1.cameraPosition.target
                    }
                    if (savedCameraPosition != null && savedZoomLevel != null) {
                        googleMap1.moveCamera(
                            CameraUpdateFactory.newCameraPosition(
                                savedCameraPosition!!
                            )
                        )
                        googleMap1.moveCamera(CameraUpdateFactory.zoomTo(savedZoomLevel!!))
                    }
                }
            }
        }

        lifecycleScope.launchWhenStarted {
            viewModel.placeWithInfo.collect { place ->
                val regex = "</?.*?>".toRegex()
                dialogBinding.tvDescription.text = place?.info?.descr?.replace(regex , "")
                Glide.with(dialogBinding.ivMain.context).load(place?.image)
                    .into(dialogBinding.ivMain)
                Log.d("MyLog" , place?.info.toString())
            }
        }
        return binding.root
    }

    override fun onViewCreated(view: View , savedInstanceState: Bundle?) {
        super.onViewCreated(view , savedInstanceState)
        if (savedInstanceState != null) {
            savedCameraPosition = savedInstanceState.getParcelable("cameraPosition")
            savedZoomLevel = savedInstanceState.getFloat("zoomLevel")
        }
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment?.getMapAsync(callback)


    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        if (::googleMap1.isInitialized) {
            outState.putParcelable("cameraPosition" , googleMap1.cameraPosition)
            outState.putFloat("zoomLevel" , googleMap1.cameraPosition.zoom)
        }
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
        if (savedInstanceState != null) {
            savedCameraPosition = savedInstanceState.getParcelable("cameraPosition")
            savedZoomLevel = savedInstanceState.getFloat("zoomLevel")
        }
    }


}
