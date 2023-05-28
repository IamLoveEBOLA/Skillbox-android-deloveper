package com.room

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.room.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var wordViewModel: WordViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        wordViewModel = ViewModelProvider(this).get(WordViewModel::class.java)
        binding.buttonAdd.isEnabled = false

        // Обновление списка первых пяти слов
        wordViewModel.topWords.observe(this) { words ->
            val topWordsString =
                words.take(5).joinToString(separator = "\n") { "${it.word}: ${it.count}" }
            binding.textViewTopWords.text = topWordsString
        }

        // Добавление слова
        binding.buttonAdd.setOnClickListener {
            val word = binding.editTextWord.text.toString().trim()
            if (word.isNotEmpty()) {
                wordViewModel.insertWord(word)
                binding.editTextWord.text.clear()
            }
        }
        // Неактивная кнопка если поле пустое
        binding.editTextWord.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                binding.buttonAdd.isEnabled = s?.isNotEmpty() == true
            }
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })

        // Очистка БД
        binding.buttonClear.setOnClickListener {
            wordViewModel.deleteAllWords()
        }
    }
}