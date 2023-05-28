package com.example.myapplication

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.myapplication.databinding.TimerViewBinding
import kotlinx.coroutines.*

class TimerView @JvmOverloads constructor(
    context: Context ,
    attrs : AttributeSet? = null ,
    defStyleAttr:Int = 0
    ):ConstraintLayout(context,attrs,defStyleAttr){
    private val binding = TimerViewBinding.inflate(LayoutInflater.from(context))
    private var timeListeners = mutableSetOf<(TimeState)->Unit>()
    private var curTime:Long=0
        set(value) {
            if (value == field)
                return
            field = value
            timeListeners.forEach{ it(TimeState(value,isPlayed))}
        }
    private var isPlayed = false
    private lateinit var job: Job
    init {
        binding.bPlay.setOnClickListener { bPlayOnClick() }
        binding.bReset.setOnClickListener { bResetOnClick() }
        binding.bReset.isEnabled = false
        binding.bPlay.text = resources.getString(R.string.start)
        binding.bReset.text = resources.getString(R.string.reset)
        addView(binding.root)
    }

    private fun bResetOnClick() {
        reset()
    }

    private fun bPlayOnClick(){

        if (isPlayed) stop()
        else start()
        isPlayed = !isPlayed
    }
    fun start(){
        binding.bReset.isEnabled = true
        binding.bPlay.text = resources.getString(R.string.stop)
        job = GlobalScope.launch {
            timer()
        }
    }
    fun stop(){
        binding.bReset.isEnabled = true
        binding.bPlay.text = resources.getString(R.string.start)
        job.cancel()
    }
    suspend fun timer()= coroutineScope{
        launch(Dispatchers.Main) {
            while (true){
                delay(1000)
                curTime++
                if (curTime==SECONDS_IN_DAY) curTime=0
            }
        }
    }
    fun reset(){
        binding.bReset.isEnabled = false
        isPlayed = false
        stop()
        curTime = 0
    }
    fun currentTime():Long{
        return curTime
    }
    fun addUpdateListener(listener:(TimeState)->Unit)=timeListeners.add(listener)
    fun removeUpdateListener(listener:(TimeState)->Unit)=timeListeners.remove(listener)
    companion object {
        private const val SECONDS_IN_DAY:Long = 86400
    }
}