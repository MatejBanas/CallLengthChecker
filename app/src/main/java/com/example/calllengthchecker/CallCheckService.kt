package com.example.calllengthchecker

import android.content.Context
import android.content.Intent
import android.media.AudioManager
import android.util.Log
import androidx.core.app.JobIntentService

class CallCheckService: JobIntentService() {

    override fun onHandleWork(intent: Intent) {
        val audioManager = getSystemService(Context.AUDIO_SERVICE) as AudioManager
        val preferences = getSharedPreferences("CallStatePreferences", MODE_PRIVATE)

        val checker = CallStateChecker(audioManager)
        val storage = CallStateStorage(preferences)
        val handler = CallCheckHandler(checker, storage)

        handler.checkAndStoreCallState()
    }

    companion object {
        private const val JOB_ID = 123

        fun enqueueWork(context: Context, intent: Intent) {
            enqueueWork(context, CallCheckService::class.java, JOB_ID, intent)
        }
    }
}