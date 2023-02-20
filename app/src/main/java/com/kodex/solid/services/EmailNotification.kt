package com.kodex.solid.services

import android.util.Log

class EmailNotification : NotificationService{
    override fun sendNotification(text: String) {
        Log.d("check", "EmailNotification: send mail notification $text" )
    }
}