package com.example.mvvm


import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.mvvm.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: ModelView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProvider(this).get(ModelView::class.java)


        val searchButton = findViewById<Button>(R.id.searchButton)
        val searchEditText = findViewById<TextView>(R.id.searchEditText)
        val progressBar = findViewById<ProgressBar>(R.id.progressBar)
        val resultTextView = findViewById<TextView>(R.id.resultTextView)
        searchButton.isEnabled = false


        searchEditText.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                searchButton.isEnabled = (s?.length ?: 0) >= 3
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }
        })



        searchButton.setOnClickListener {
            viewModel.search(searchEditText.text.toString())
        }
        viewModel.searchResult.observe(this , Observer { result ->
            resultTextView.text = result
        })
        viewModel.showProcess.observe(this , Observer { show ->
            progressBar.visibility = if (show) View.VISIBLE else View.GONE
        })
        viewModel.showError.observe(this , Observer { show ->
            if (show) {
                Toast.makeText(this , "Произошла ошибка" , Toast.LENGTH_SHORT).show()
            }
        })


    }
}