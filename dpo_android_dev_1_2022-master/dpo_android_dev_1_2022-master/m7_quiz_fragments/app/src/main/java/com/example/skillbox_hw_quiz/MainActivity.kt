package com.example.skillbox_hw_quiz


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import com.example.skillbox_hw_quiz.databinding.MainActivityBinding
import com.example.skillbox_hw_quiz.ui.main.WelcomeFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = MainActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)





//        supportFragmentManager.commit {
//            addToBackStack(WelcomeFragment::class.java.simpleName)
//
//        }

    }
}
