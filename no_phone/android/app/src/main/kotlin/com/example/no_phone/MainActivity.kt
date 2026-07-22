package com.example.no_phone

import io.flutter.embedding.android.FlutterActivity
import io.flutter.embedding.engine.FlutterEngine
import io.flutter.plugin.common.MethodChannel

class MainActivity : FlutterActivity() {
    companion object{
        private val CHANNEL = "com.example.no_phone"
    }

    override fun configureFlutterEngine(flutterEngine: FlutterEngine) {
        super.configureFlutterEngine(flutterEngine) //I think super requires this argument

        MethodChannel(flutterEngine.dartExecutor.binaryMessenger, CHANNEL).setMethodCallHandler {
            call, result ->
                when (call.method) {
                    "testConnection" -> {
                        result.success(
                            "Flutter can be reached to Kotlin"
                        )
                    }

                    else -> {
                        result.notImplemented()
                    }
                }
        }
    }
}