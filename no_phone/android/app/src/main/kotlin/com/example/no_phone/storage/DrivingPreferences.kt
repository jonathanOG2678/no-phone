package com.example.no_phone.storage

import android.content.Context

class DrivingPreferences(context: Context) {
    companion object {
        private const val PREFERENCES_NAME = "driving_settings"
        private const val MONITORING_ENABLED_KEY = "monitoring_enabled"
        private const val OVERLAY_ENABLED_KEY = "overlay_enabled"
        private const val BACKGROUND_USE_ENABLED_KEY = "background_use_enabled"
    }


    private val prefstorage = context.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE)

    fun setMonitoringEnabled(enabled: Boolean) {
        prefstorage.edit().putBoolean("monitoring_enabled", enabled).apply()        
    }

    fun getMonitoringEnabled(): Boolean {
        return prefstorage.getBoolean(MONITORING_ENABLED_KEY, false)
    }

    fun setOverlayEnabled(enabled: Boolean) {
        prefstorage.edit().putBoolean("overlay_enabled", enabled).apply()
    }

    fun getOverlayEnabled(): Boolean {
        return prefstorage.getBoolean(OVERLAY_ENABLED_KEY, false)
    }

    fun setBackgroundUseEnabled(enabled: Boolean) {
        prefstorage.edit().putBoolean("background_use_enabled", enabled).apply()
    }

    fun getBackgroundUseEnabled(): Boolean {
        return prefstorage.getBoolean(BACKGROUND_USE_ENABLED_KEY, false)
    }

}