package com.example.no_phone

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import com.google.android.gms.location.ActivityTransitionResult


/* What this file contains:
* - An ActivityTransitionReceiver object that calls onReceive() with a list of events, can be used to extract info
* - Determine whether the user entered/exited the vehicle
* - Send result to rest of application
* */

class ActivityTransitionReceiver: BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        if (ActivityTransitionResult.hasResult(intent)) {
            val result = ActivityTransitionResult.extractResult(intent)
            for (event in result!!.getTransitionEvents()) {
              Log.d("ActivityTransitionReceiver", event.toString())
                
            }
        }
    }


}