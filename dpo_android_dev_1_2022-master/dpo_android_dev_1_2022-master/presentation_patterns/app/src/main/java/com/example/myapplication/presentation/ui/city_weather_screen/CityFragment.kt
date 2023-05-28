package com.example.myapplication.presentation.ui.city_weather_screen


import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import com.example.myapplication.R
import com.example.myapplication.App.Companion.appComponent
import com.example.myapplication.data.models.saved_city.SavedCity
import com.example.myapplication.databinding.FragmentCityBinding
import com.example.myapplication.presentation.utils.DateFormatter
import kotlinx.coroutines.launch

class CityFragment : Fragment() {
    private var _binding: FragmentCityBinding? = null
    private val binding get() = _binding!!

    private val args: CityFragmentArgs by navArgs<CityFragmentArgs>()
    private val cityViewModel: CityFragmentViewModel by viewModels { appComponent.cityViewModelFactory() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCityBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        cityViewModel.loadTemperature(args.cityListItem.key)

        val context = requireContext()
        binding.cityName.text =
            context.resources.getString(R.string.city_name, args.cityListItem.localizedName)
        binding.regionName.text = context.resources.getString(
            R.string.region_name,
            args.cityListItem.region.localizedName
        )
        binding.countryName.text = context.resources.getString(
            R.string.country_name,
            args.cityListItem.country.localizedName
        )
        binding.geoPosition.text = context.resources.getString(
            R.string.geoPosition,
            args.cityListItem.geoPosition.latitude.toString(),
            args.cityListItem.geoPosition.longitude.toString()
        )


        viewLifecycleOwner.lifecycleScope.launch {
            cityViewModel.city.collect {
                binding.temperature.text = context.resources.getString(
                    R.string.temperature,
                    it?.temperature?.minimum?.value.toString(),
                    it?.temperature?.maximum?.value.toString()
                )
                Log.d("TAG", "getDate:${it?.date}")

                if (it?.date != null) {
                    val formattedDate = DateFormatter(
                        date = it.date,
                        inputPattern = "yyyy-MM-dd'T'hh:mm:ssZ",
                        outputPattern = "dd-MM-yyyy"
                    )
                        .format()
                    binding.date.text = context.getString(R.string.date, formattedDate)

                    val savedCity = SavedCity(
                        id = it.date + args.cityListItem.englishName,
                        city_name = args.cityListItem.localizedName,
                        region = args.cityListItem.region.localizedName,
                        country = args.cityListItem.country.localizedName,
                        latitude = args.cityListItem.geoPosition.latitude.toString(),
                        longitude = args.cityListItem.geoPosition.longitude.toString(),
                        t_min = it.temperature.minimum.value.toString(),
                        t_max = it.temperature.maximum.value.toString(),
                        date = formattedDate
                    )

                    cityViewModel.saveCity(savedCity)
                }
            }
        }
    }
}