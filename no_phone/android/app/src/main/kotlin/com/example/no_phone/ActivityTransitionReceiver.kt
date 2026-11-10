package com.example.no_phone

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import com.google.android.gms.location.ActivityTransition
import com.google.android.gms.location.ActivityTransitionResult
import com.google.android.gms.location.DetectedActivity
import android.os.SystemClock
import java.util.Locale

/* What this file contains:
* - An ActivityTransitionReceiver object that calls onReceive() with a list of events, can be used to extract info
* - Determine whether the user entered/exited the vehicle
* - Send result to rest of application (WIP)
* */

class ActivityTransitionReceiver: BroadcastReceiver() {
    private companion object {
        const val TAG = "ActivityTransitionReceiver"
    }

    override fun onReceive(context: Context, intent: Intent) {
        if (!ActivityTransitionResult.hasResult(intent)) {
            Log.w(TAG, "Broadcast contained no transition result")
            return
        }

        val result = ActivityTransitionResult.extractResult(intent) ?: return

        //Display events in Logcat with custom labels for easier reading, AI generated
        for (event in result.transitionEvents) {
            val activityName =
                when (event.activityType) {
                    DetectedActivity.IN_VEHICLE ->
                        "IN_VEHICLE"

                    else ->
                        "UNKNOWN_ACTIVITY_${event.activityType}"
                }

            val transitionName =
                when (event.transitionType) {
                    ActivityTransition.ACTIVITY_TRANSITION_ENTER ->
                        "ENTER"

                    ActivityTransition.ACTIVITY_TRANSITION_EXIT ->
                        "EXIT"

                    else ->
                        "UNKNOWN_TRANSITION_${event.transitionType}"
                }
            val receivedAtNanos =
                SystemClock.elapsedRealtimeNanos()

            val deliveryDelaySeconds =
                (receivedAtNanos - event.elapsedRealTimeNanos) /
                        1_000_000_000.0
            //Log.d(TAG, "$activityName: $transitionName")
            //Log.d(TAG, String.format(Locale.US, "%s: %s | delivery delay: %.2f seconds", activityName, transitionName, deliveryDelaySeconds))
            Log.d(
                TAG,
                String.format(
                    Locale.US,
                    "%s: %s | eventNanos=%d | receivedNanos=%d | deliveryDelay=%.2f seconds",
                    activityName,
                    transitionName,
                    event.elapsedRealTimeNanos,
                    receivedAtNanos,
                    deliveryDelaySeconds
                )
            )
        }
    }

}