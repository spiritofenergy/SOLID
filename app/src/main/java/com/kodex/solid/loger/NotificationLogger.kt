package com.kodex.solid.loger

import android.util.Log
import com.kodex.solid.services.DataBase

open class NotificationLogger {
    open fun logNotification(msg: String){
        Log.d("check", "NotificationLogger: logNotification $msg")
    }
}
class EmailNotificationLogger(private val dataBase: DataBase): NotificationLogger(){

    override fun logNotification(msg: String) {
        dataBase.save(msg)
        Log.d("check", "CustomNotificationLogger: logNotification $msg")
    }
}
class ToastNotificationLogger : NotificationLogger(){
    override fun logNotification(msg: String) {
        Log.d("check", "CustomNotificationLogger: logNotification $msg")
    }
}