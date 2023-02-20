package com.kodex.solid

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import com.kodex.solid.loger.EmailNotificationLogger
import com.kodex.solid.loger.NotificationLogger
import com.kodex.solid.loger.ToastNotificationLogger
import com.kodex.solid.screens.MainScreen
import com.kodex.solid.services.EmailNotification
import com.kodex.solid.services.ToastNotification
import com.kodex.solid.services.UpdateUiService
import com.kodex.solid.ui.theme.SOLIDTheme

class MainActivity : ComponentActivity() {
    enum class NotificationType{
        TOAST, EMAIL
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val notificationType = NotificationType.TOAST
        val updateUiService = UpdateUiService()
        val notificationLogger = when(notificationType){
            NotificationType.TOAST -> ToastNotificationLogger()
            NotificationType.EMAIL -> EmailNotificationLogger()
        }

        setContent {
            SOLIDTheme {
                Surface(modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background) {

                    MainScreen(
                        contex = applicationContext,
                        updateUiService = updateUiService,
                        notificationType = notificationType,
                        notificationLogger = notificationLogger
                    )
                }
            }
        }
    }
}


/*
@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    SOLIDTheme {
        Greeting("Android")
    }
}*/
