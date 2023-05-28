package com.example.myapplication

import android.content.Context
import android.content.Intent
import android.provider.AlarmClock
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.example.background.workers.makeStatusNotification
import dagger.hilt.android.qualifiers.ApplicationContext
import org.shredzone.commons.suncalc.SunTimes
import java.time.Duration
import java.time.ZoneId
import java.time.ZonedDateTime
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class SunRiseWorker @Inject constructor(@ApplicationContext private val context: Context , workerParams: WorkerParameters):
    Worker(context,workerParams) {
    override fun doWork(): Result {
        return try {
            val tz = inputData.getString(TZ_KEY)
            val h = inputData.getInt(HOURS_KEY,0)
            val m = inputData.getInt(MINUTES_KEY,0)
            val localDateTime: ZonedDateTime = ZonedDateTime.now()
            val Zone: ZoneId = ZoneId.of(tz!!.removeRange(0,12))
            val tzDateTime: ZonedDateTime = localDateTime.withZoneSameInstant(Zone)
            var times = SunTimes.compute().on(tzDateTime).execute()
            //check if sun already rise today, so we take next day
            if (times.rise!=null && times.rise!!.isBefore(tzDateTime)){
                times = SunTimes.compute().on(tzDateTime.plusDays(1)).execute()
            }
            val difference = Duration.between(tzDateTime,times.rise).toMillis()
            val timeTask = difference + TimeUnit.HOURS.toMillis(h!!.toLong()) + TimeUnit.MINUTES.toMillis(m!!.toLong())

            val intent: Intent = Intent(AlarmClock.ACTION_SET_ALARM)
            val alarmtime = localDateTime.plusMinutes(TimeUnit.MILLISECONDS.toMinutes(timeTask))

            intent.putExtra(AlarmClock.EXTRA_HOUR,h)
            intent.putExtra(AlarmClock.EXTRA_MINUTES,m)
            intent.putExtra(AlarmClock.EXTRA_SKIP_UI,true)
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent)
            makeStatusNotification("alarm will go off in $timeTask mils milliseconds",context )
            Result.success()
        }
        catch (throwable: Throwable) {
            Log.e("MyLog", "Error applying blur")
            Result.failure()
        }
    }
    companion object{
        const val TZ_KEY:String = "tzkey"
        const val HOURS_KEY:String = "hourskey"
        const val MINUTES_KEY:String = "minuteskey"
    }
}