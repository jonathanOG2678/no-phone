package com.example.no_phone.storage

import android.content.Context
import android.content.SharedPreferences

class DrivingPreferences(context: Context) {
    companion object {
        private const val PREFERENCES_NAME = "driving_settings"
        private const val MONITORING_ENABLED_KEY = "monitoring_enabled"
    }


    private val prefstorage = context.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE)

    fun setMonitoringEnabled(enabled: Boolean) {
        prefstorage.edit().putBoolean("monitoring_enabled", enabled).apply()        
    }

    fun getMonitoringEnabled(): Boolean {
        return prefstorage.getBoolean(MONITORING_ENABLED_KEY, false)
    }

}