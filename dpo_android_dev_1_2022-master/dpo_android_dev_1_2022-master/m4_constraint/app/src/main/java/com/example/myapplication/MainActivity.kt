package com.example.myapplication

import android.media.MediaPlayer
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {
    lateinit var mPlayer: MediaPlayer
    lateinit var playButton :Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mPlayer = MediaPlayer.create(this, R.raw.music)
        play()






    }



    fun play() {
        mPlayer.start()
    }








}