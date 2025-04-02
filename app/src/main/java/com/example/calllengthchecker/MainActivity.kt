package com.example.calllengthchecker

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private lateinit var preferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        PollingScheduler.scheduleAlarm(applicationContext)

        preferences = getSharedPreferences("CallStatePreferences", Context.MODE_PRIVATE)

        val btnCallLength = findViewById<Button>(R.id.btnCallLength)
        btnCallLength.setOnClickListener {
            val callStart = preferences.getLong("timestamp", 0L)

            if (callStart != 0L) {
                val durationInSeconds = (System.currentTimeMillis() - callStart) / 1000
                Toast.makeText(this, "Call duration: $durationInSeconds seconds", Toast.LENGTH_LONG).show()
            } else {
                Toast.makeText(this, "Not in call", Toast.LENGTH_LONG).show()
            }
        }
    }
}

// manifest declared receivers only limited exceptions for implicit broadcasts
// alarmManager is susceptible to Doze mode restrictions, ACTION_REQUEST_IGNORE_BATTERY_OPTIMIZATIONS
// JobIntentService deprecation vs workManager and PeriodicWorkRequest, minimal interval is 15 minutes
// SCHEDULE_EXACT_ALARM / USE_EXACT_ALARM permission problem, one needs user interaction the other will probably not pass Play Store review, dont use exact?