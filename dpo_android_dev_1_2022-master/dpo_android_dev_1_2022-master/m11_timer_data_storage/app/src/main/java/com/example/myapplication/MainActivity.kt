package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.example.myapplication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
    private lateinit var repository: Repository


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_main)


        repository = Repository(this)

        val editText = findViewById<EditText>(R.id.editText)
        val saveButton = findViewById<Button>(R.id.saveButton)
        val loadButton = findViewById<Button>(R.id.loadButton)
        val clearButton = findViewById<Button>(R.id.clearButton)
        val textField = findViewById<TextView>(R.id.textView)


        saveButton.setOnClickListener {
            val text = editText.text.toString()
            repository.saveText(text)
        }

        clearButton.setOnClickListener {
            repository.clearText()
        }
        loadButton.setOnClickListener {
            textField.text = repository.getText()
        }
    }
}

