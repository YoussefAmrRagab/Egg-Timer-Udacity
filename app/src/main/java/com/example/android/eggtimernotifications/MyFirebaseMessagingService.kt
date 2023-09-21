package com.example.android.eggtimernotifications;

import android.app.NotificationManager
import android.util.Log
import androidx.core.content.ContextCompat
import com.example.android.eggtimernotifications.util.sendNotification
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class MyFirebaseMessagingService : FirebaseMessagingService() {
    override fun onMessageReceived(message: RemoteMessage) {
        super.onMessageReceived(message)
        Log.d("TAG", "From: ${message.from}")

        message.data.let {
            Log.d("TAG", "Message data payload: $it")
        }

        message.notification?.let {
            Log.d("TAG", "Message Notification Body: ${it.body}")
            sendNotification(it.body!!)
        }
    }

    private fun sendNotification(message: String) {
        val notificationManager = ContextCompat.getSystemService(
            applicationContext,
            NotificationManager::class.java
        ) as NotificationManager
        notificationManager.sendNotification(message, applicationContext)
    }

    override fun onNewToken(token: String) {
        super.onNewToken(token)
        Log.d("TAG", "Refreshed token: $token")
        sendRegistrationToServer(token)
    }

    private fun sendRegistrationToServer(token: String) {
        // TODO: send user token to server to save it

        /* Here you can send the token to the server to save it and reuse it when sending a notification to a specific user
               for sending notification to specific user you can send a POST request to fcm URL => https://fcm.googleapis.com/fcm/send
               with Headers => Authorization: key=SERVER_KEY
               and Body =>  {
                                "to": token of user that you want to send him notification,
                                "data": {
                                "title": notification-title
                                "message": notification-message
                                }
                            }
        */
    }
}