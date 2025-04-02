package com.example.calllengthchecker

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent

class PollingReceiver: BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {
        if (context == null || intent?.action == null) return

        when (intent.action) {
            ACTION_CHECK_CALL_STATE,
            Intent.ACTION_SCREEN_ON,
            Intent.ACTION_USER_PRESENT -> {
                CallCheckService.enqueueWork(context, intent)
                PollingScheduler.scheduleAlarm(context)
            }

            Intent.ACTION_BOOT_COMPLETED -> {
                PollingScheduler.scheduleAlarm(context)
            }
        }
    }

    companion object {
        const val ACTION_CHECK_CALL_STATE = "com.example.calllengthchecker.action.CHECK_CALL_STATE"
    }

}