package com.example.timer_first



import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import com.google.android.material.slider.Slider
import kotlinx.coroutines.*


private const val TAG = "MainActivity"

class MainActivity : AppCompatActivity() {
    private var currentProgress = 5
    private lateinit var slider: Slider
    private lateinit var progressBar: ProgressBar
    private lateinit var progressText: TextView
    private lateinit var button: Button
    private val scope = CoroutineScope(Dispatchers.Default)
    private var isTimerActive = false
    var timeJob: Job? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        slider = findViewById(R.id.slider)
        progressBar = findViewById(R.id.progressBar3)
        progressText = findViewById(R.id.count)
        button = findViewById(R.id.button)


        if (savedInstanceState != null) {
            with(savedInstanceState)
            {
                isTimerActive = getBoolean("timer")
                currentProgress = getInt("progress")
                slider.isEnabled = getBoolean("slider")
                progressBar.progress = getInt("progressBar")
                progressText.text = getCharSequence("progressText")
                button.text = getCharSequence("button")
            }
        }

        setDefault()

        slider.addOnChangeListener { _, _, _ ->
            currentProgress = slider.value.toInt()
            progressText.text = currentProgress.toString()
            progressBar.max = currentProgress
            progressBar.progress = currentProgress
        }


        button.setOnClickListener {
            if (!isTimerActive) {
                isTimerActive = true
                slider.isEnabled = false
                button.setText(R.string.stop)
                timeJob = scope.launch(Dispatchers.Main) {
                    timerStart()
                    isTimerActive = false
                    progressText.text = slider.value.toString()
                    progressBar.progress = slider.value.toInt()
                    button.setText(R.string.start)
                    Toast.makeText(applicationContext, "Timer tast Finished", Toast.LENGTH_SHORT)
                        .show()
                    slider.isEnabled = true
                }
                scope.launch {
                    while (true) {
                        if (!isTimerActive) {
                            timeJob?.cancel()
                            updateView(currentProgress)
                        } else {
                            timeJob?.start()
                        }
                    }
                }
            } else {
                isTimerActive = false
                Toast.makeText(applicationContext, "Timer tast Finished", Toast.LENGTH_SHORT).show()
                setDefault()
                slider.isEnabled = true
            }
        }

    }


    override fun onSaveInstanceState(outState: Bundle) {
        outState.run {
            putBoolean("timer", isTimerActive)
            putInt("progress", currentProgress)
            putBoolean("slider", slider.isEnabled)
            putInt("progressBar", progressBar.progress)
            putCharSequence("progressText", progressText.text)
            putCharSequence("button", button.text)
        }
        super.onSaveInstanceState(outState)
    }

    private suspend fun timerStart() {
        while (currentProgress > 0) {
            currentProgress--
            Log.d(TAG, "${currentProgress}")
            progressText.text = currentProgress.toString()
            progressBar.progress = currentProgress
            delay(1000)
        }
    }


    private fun setDefault() {
        progressText.text = currentProgress.toString()
        progressBar.max = currentProgress
        progressBar.progress = currentProgress
        button.setText(R.string.start)
    }


    private suspend fun updateView(millis: Int) {
        withContext(Dispatchers.Main) {
            progressText.text = millis.toString()
            progressBar.progress = millis
        }
    }




}
































