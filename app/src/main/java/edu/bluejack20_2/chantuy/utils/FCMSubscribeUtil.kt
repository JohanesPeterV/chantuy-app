package edu.bluejack20_2.chantuy.utils

import android.content.Context
import android.util.Log
import android.widget.Toast
import com.google.firebase.messaging.FirebaseMessaging

class FCMSubscribeUtil {
    companion object {
        private val TOPIC = "reminder-topic"
        private val TAG = "FCMSubscriberUtil"

        fun subscribe() {
            FirebaseMessaging.getInstance().subscribeToTopic("reminder-topic")
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        Log.i(TAG, "Succesfully subscribed")
                    } else {
                        Log.i(TAG, "subscribe failed")
                    }
                }
        }

        fun unsubscribe() {
            FirebaseMessaging.getInstance().unsubscribeFromTopic("reminder-topic")
                .addOnCompleteListener {task ->
                    if(task.isSuccessful) {
                        Log.i(TAG, "Succesfully unsubsubscribed")
                    } else {
                        Log.i(TAG, "unsubscribe failed")
                    }
                }
        }
    }
}