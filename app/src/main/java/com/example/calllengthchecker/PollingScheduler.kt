package com.example.calllengthchecker

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent

object PollingScheduler {
    private const val INTERVAL_MS = 10000
    private const val ACTION_CHECK_CALL_STATE = "com.example.calllengthchecker.action.CHECK_CALL_STATE"

    fun scheduleAlarm(context: Context) {
        val intent = Intent(ACTION_CHECK_CALL_STATE).setPackage(context.packageName)
        val pendingIntent = PendingIntent.getBroadcast(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE)

        val triggerTime = System.currentTimeMillis() + INTERVAL_MS
        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager

        alarmManager.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, triggerTime, pendingIntent) // permission problem
//        alarmManager.setAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, triggerTime, pendingIntent)
    }

}