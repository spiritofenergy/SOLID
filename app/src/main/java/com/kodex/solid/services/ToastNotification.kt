package com.kodex.solid.services

import android.content.Context
import android.util.Log
import android.widget.Toast

class ToastNotification(private val context: Context): NotificationService {
    override fun sendNotification(text: String)
    {
        Log.d("check", "send notification $text")
        Toast.makeText(context,"Notification: $text",Toast.LENGTH_SHORT).show()
    }
}