package com.example.myapplication

import android.app.Application
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.graphics.Color
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.ktx.Firebase
import com.google.firebase.messaging.FirebaseMessaging
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class App:Application() {
    lateinit var token: String
    override fun onCreate() {
        super.onCreate()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {createNotificationChannel()}

        FirebaseMessaging.getInstance().token.addOnCompleteListener(OnCompleteListener{ task ->
            if (!task.isSuccessful){
                return@OnCompleteListener
            }
            token = task.result
            Log.d("token",token)
        })
    }
   @RequiresApi(Build.VERSION_CODES.O)
    fun createNotificationChannel(){
        val name = getString(R.string.channel_name)
        val descriptionText = getString(R.string.channel_description)
        val importance = NotificationManager.IMPORTANCE_HIGH


       val mChannel = NotificationChannel(CHANNEL_ID, name , importance).apply {
           setShowBadge(true)
           lockscreenVisibility = Notification.VISIBILITY_PUBLIC
           lightColor = Color.RED
           vibrationPattern = longArrayOf(100 , 200 , 300 , 400 , 500 , 400 , 300 , 200 , 400)
           setImportance(importance)
           description = description
           setBypassDnd(true)
           lockscreenVisibility = Notification.VISIBILITY_PRIVATE
           enableVibration(true)
           lightColor = Color.GREEN
           enableLights(true)


       }

       mChannel.description = descriptionText
        val notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.createNotificationChannel(mChannel)
    }





    companion object{
        const val CHANNEL_ID ="my_channel_id"
    }
}