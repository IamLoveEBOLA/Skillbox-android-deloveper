package com.example.myapplication

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.work.Data
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.WorkRequest
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    @ApplicationContext private val context: Context,
    private val timeZonesList: TimeZonesList
): ViewModel() {
    fun getTimezonesList():MutableList<String?>{
        return timeZonesList.getTimeZoneList(TimeZonesList.OffsetBase.UTC)?.toMutableList() ?: mutableListOf()
    }
    fun setAlarm(tz:String,h:Int,m:Int){

        val timeWorkRequest: WorkRequest = OneTimeWorkRequestBuilder<SunRiseWorker>()
            .setInputData(createInputDataForWorker(tz,h,m)).build()
        WorkManager
            .getInstance(context)
            .enqueue(timeWorkRequest)
        /*val localDateTime: ZonedDateTime = ZonedDateTime.now()
        val Zone: ZoneId = ZoneId.of(tz.removeRange(0,12))
        val tzDateTime: ZonedDateTime = localDateTime.withZoneSameInstant(Zone)
        var times = SunTimes.compute().on(tzDateTime).execute()
        //check if sun already rise today, so we take next day
        if (times.rise!=null && times.rise!!.isBefore(tzDateTime)){
            times = SunTimes.compute().on(tzDateTime.plusDays(1)).execute()
        }
        val difference = Duration.between(tzDateTime,times.rise).toMillis()
        val timeTask = difference +TimeUnit.HOURS.toMillis(h.toLong()) + TimeUnit.MINUTES.toMillis(m.toLong())*/

    }
    private fun createInputDataForWorker(tz:String,
                                         h:Int,
                                         m:Int): Data {
        val builder = Data.Builder()
        builder.putString(SunRiseWorker.TZ_KEY,tz)
        builder.putInt(SunRiseWorker.HOURS_KEY, h)
        builder.putInt(SunRiseWorker.MINUTES_KEY, m)

        return builder.build()
    }
}