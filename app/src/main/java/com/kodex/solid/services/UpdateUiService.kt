package com.kodex.solid.services

import android.util.Log

class UpdateUiService() {

    fun updateNotification(text: String): String {
        Log.d("check", "updateNotification $text")
        return text.uppercase()
    }
}