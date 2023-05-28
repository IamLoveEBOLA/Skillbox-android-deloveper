package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.myapplication.dagger_bicycle.BicycleFactory
import com.example.myapplication.dagger_bicycle.DaggerBicycleComponent
import com.example.myapplication.databinding.ActivityMainBinding
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var dagger : DaggerBicycleComponent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        binding.bDagger.setOnClickListener { bDaggerOnClick() }
        binding.bKoin.setOnClickListener { bKoinOnClick() }
        dagger = (application as App).dagger
        setContentView(binding.root)

    }
    private fun bDaggerOnClick(){
        val bicycleFactory1 = dagger.getBicycleFactory()
        val bicycle = bicycleFactory1.build("Dagger","Red")
        Toast.makeText(this,bicycle.toString(),Toast.LENGTH_SHORT).show()
        Log.d("MyLog",bicycle.toString())
    }
    private fun bKoinOnClick(){
        val bicycleFactory1:BicycleFactory by inject()
        val bicycle = bicycleFactory1.build("Koin","Blue")
        Toast.makeText(this,bicycle.toString(),Toast.LENGTH_SHORT).show()
        Log.d("MyLog",bicycle.toString())
    }
}