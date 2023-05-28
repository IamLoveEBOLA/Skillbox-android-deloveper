package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.myapplication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding:ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.timerView.addUpdateListener { binding.tvTime.text = timeToString(it.time) }
        binding.timerView.addUpdateListener { binding.clock.setTime(it.time)}
        binding.tvTime.text = timeToString(0)

    }
    private fun timeToString(time:Long):String{
        val h = time/60/60
        val m = time/60%60
        val s = time%60
        return String.format("%02d:%02d:%02d",h,m,s)
    }
}