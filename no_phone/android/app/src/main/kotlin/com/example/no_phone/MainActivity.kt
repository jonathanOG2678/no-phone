package com.example.no_phone

import com.example.no_phone.storage.DrivingPreferences
import io.flutter.embedding.engine.FlutterEngine
import io.flutter.plugin.common.MethodChannel
import android.Manifest
import androidx.activity.result.contract.ActivityResultContracts
import android.util.Log
import io.flutter.embedding.android.FlutterFragmentActivity


class MainActivity : FlutterFragmentActivity() {
    private companion object{
        const val CHANNEL = "com.example.no_phone"
        const val TAG = "MainActivity"
    }

    private lateinit var drivingPreferences: DrivingPreferences
    private lateinit var drivingMonitor: DrivingMonitor

    //Asks the user for permission "Activity Recognition"
    private val activityRecognitionPermissionLauncher = registerForActivityResult(ActivityResultContracts.RequestPermission()) {
        permissionGranted ->
            if (permissionGranted) {
                Log.d(TAG, "Activity Recognition permission granted")
                drivingMonitor.enableMonitoring()
            } else {
                Log.w(TAG, "Activity Recognition permission denied")
            }
    }

    private fun startDrivingMonitoring() {
        if (drivingMonitor.hasActivityRecognitionPermission()) {
            Log.d(TAG, "Permission already granted")
            drivingMonitor.enableMonitoring()
        } else {
            Log.d(TAG, "Requesting Activity Recognition permission")
            activityRecognitionPermissionLauncher.launch(Manifest.permission.ACTIVITY_RECOGNITION)
        }
    }

    override fun configureFlutterEngine(flutterEngine: FlutterEngine) {
        super.configureFlutterEngine(flutterEngine) //I think super requires this argument

        drivingPreferences = DrivingPreferences(this)
        drivingMonitor = DrivingMonitor(this)
        Log.d("EXPORT_CHECK", "EXPORT_CHECK_001")

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

                        //Call enable or disable monitoring based on saved setting
                        if (enabled) {
                            Log.d(TAG, "startDrivingMonitoring() called")
                            startDrivingMonitoring()
                        }
                        else {
                            Log.d(TAG, "disableMonitoring() from drivingMonitor called")
                            drivingMonitor.disableMonitoring()
                        }


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