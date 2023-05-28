package com.example.myapplication

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RemoteViews;
import com.example.myapplication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_main)

        setContentView(binding.root)


        var counter = 0
        var maxcounter = 50

        fun main() {



            binding.StatusText.setTextColor(Color.GREEN)
            binding.TextTest.text = counter.toString()
            binding.buttonMinus.isEnabled = false
            binding.buttonPlus.isEnabled = true
            binding.StatusText.text = "Все места свободны"
            binding.buttonReset.visibility = View.INVISIBLE
            if (counter in 1 until maxcounter) {
                binding.StatusText.setTextColor(Color.BLUE)
                binding.StatusText.text =
                    "Осталось свободных мест " + (maxcounter - counter).toString()
                binding.buttonMinus.isEnabled = true
            } else if (counter == maxcounter) {
                binding.StatusText.setTextColor(Color.RED)
                binding.buttonPlus.isEnabled = false
                binding.StatusText.text = "Все места заняты"
                binding.buttonReset.visibility = View.VISIBLE
                binding.buttonMinus.isEnabled = true

            }

        }
        binding.buttonPlus.setOnClickListener {
            counter++
            main()
        }
        binding.buttonMinus.setOnClickListener {
            counter--
            main()
        }
        binding.buttonReset.setOnClickListener {
            counter = 0
            main()
        }


    }
}