package com.kodex.solid.loger

import android.util.Log

open class NotificationLogger {
    open fun logNotification(msg: String){
        Log.d("check", "NotificationLogger: logNotification $msg")
    }
}
class EmailNotificationLogger: NotificationLogger(){

    override fun logNotification(msg: String) {
        Log.d("check", "CustomNotificationLogger: logNotification $msg")
    }
}
class ToastNotificationLogger : NotificationLogger(){
    override fun logNotification(msg: String) {
        Log.d("check", "CustomNotificationLogger: logNotification $msg")
    }
}