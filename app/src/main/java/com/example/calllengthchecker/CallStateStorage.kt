package com.example.calllengthchecker

import android.content.SharedPreferences

class CallStateStorage(private val prefereces: SharedPreferences) {

    fun saveCallState(active: Boolean, timestamp: Long) {
        prefereces.edit()
            .putBoolean(ACTIVE_KEY, active)
            .putLong(TIMESTAMP_KEY, timestamp)
            .apply()
    }

    fun getCallActive() :Boolean {
        return prefereces.getBoolean(ACTIVE_KEY, false)
    }

    fun getCallTimestamp(): Long {
        return prefereces.getLong(TIMESTAMP_KEY, 0L)
    }

    companion object {
        private const val ACTIVE_KEY = "active"
        private const val TIMESTAMP_KEY = "timestamp"
    }
}