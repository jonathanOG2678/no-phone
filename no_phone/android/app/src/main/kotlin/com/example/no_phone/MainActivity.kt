package com.example.no_phone

import com.example.no_phone.storage.DrivingPreferences
import io.flutter.embedding.android.FlutterActivity
import io.flutter.embedding.engine.FlutterEngine
import io.flutter.plugin.common.MethodChannel

class MainActivity : FlutterActivity() {
    companion object{
        private val CHANNEL = "com.example.no_phone"
    }

    private lateinit var drivingPreferences: DrivingPreferences

    override fun configureFlutterEngine(flutterEngine: FlutterEngine) {
        super.configureFlutterEngine(flutterEngine) //I think super requires this argument

        drivingPreferences = DrivingPreferences(this)


        MethodChannel(flutterEngine.dartExecutor.binaryMessenger, CHANNEL).setMethodCallHandler {
            call, result ->
                when (call.method) {
                    //To be deleted, or use as a quick test before operations?
                    "testConnection" -> {
                        result.success("Flutter has reached Kotlin")
                    }

                    "setMonitoringEnabled" -> {
                        //take the argument passed
                        val enabled = call.argument<Boolean>("enabled")
                        //validate a boolean was provided
                        if (enabled == null) {
                            result.error("INVALID_ARGUMENT", "monitoringEnabled value not provided", null)
                            return@setMethodCallHandler
                        }

                        
                        //send to sharedPref to update
                        drivingPreferences.setMonitoringEnabled(enabled)
                        //call getMonitoringEnabled() to return
                        val savedValue = drivingPreferences.getMonitoringEnabled()

                        result.success(savedValue)
                    }

                    "getMonitoringEnabled" -> {
                        val savedValue = drivingPreferences.getMonitoringEnabled()

                        result.success(savedValue)                        
                    }

                    else -> {
                        result.notImplemented()
                    }
                }

        }
    }
}