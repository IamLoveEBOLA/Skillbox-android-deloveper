package com.example.myapplication

import android.R
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.activity.viewModels
import com.example.myapplication.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    private val viewModel:MainViewModel by viewModels()
    lateinit var ids :MutableList<String?>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initTimePickers()
        initSpinner()
        binding.bsetAlarm.setOnClickListener { setOnClick() }


    }

    private fun setOnClick() {
        val h =binding.numPickHour.value
        val m =binding.numPickMin.value

        val tz = binding.spinner.selectedItem.toString()
        viewModel.setAlarm(tz,h,m)
    }

    private fun initTimePickers(){
        binding.apply {
            numPickHour.maxValue = 23
            numPickHour.minValue = 0
            numPickMin.maxValue = 59
            numPickMin.minValue = 0
        }
    }
    private fun initSpinner(){
        val spinner: Spinner = binding.spinner
        ids = viewModel.getTimezonesList()
        ids?.let {
            val spinnerArrayAdapter: ArrayAdapter<String> = ArrayAdapter<String>(this, R.layout.simple_spinner_item, ids)
                .also { adapter ->
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                    spinner.adapter = adapter
                }
        }
    }
}