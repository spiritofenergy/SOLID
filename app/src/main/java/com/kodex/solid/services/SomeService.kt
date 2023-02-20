package com.kodex.solid.services

import android.util.Log

class SomeService {

    fun sendNotification(text : String){
        Log.d("check", "send notification $text")
    }

    fun updateNotification(text: String): String {
        Log.d("check", "updateNotification $text")
        return text.uppercase()
    }
}