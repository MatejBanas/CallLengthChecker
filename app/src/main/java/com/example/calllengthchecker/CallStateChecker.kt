package com.example.calllengthchecker

import android.media.AudioManager

class CallStateChecker(private val audioManager: AudioManager) {
    fun isInCall(): Boolean {
        return when (audioManager.mode) {
            AudioManager.MODE_IN_CALL, AudioManager.MODE_IN_COMMUNICATION -> true
            else -> false
        }
    }
}