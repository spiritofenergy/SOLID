package com.kodex.solid

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import com.kodex.solid.listener.ClickListener
import com.kodex.solid.listener.SwipeListener
import com.kodex.solid.loger.EmailNotificationLogger
import com.kodex.solid.loger.ToastNotificationLogger
import com.kodex.solid.screens.ExperimentalMaterial3Api
import com.kodex.solid.screens.MainScreen
import com.kodex.solid.services.UpdateUiService
import com.kodex.solid.ui.theme.SOLIDTheme

@ExperimentalMaterialApi
@ExperimentalMaterial3Api
class MainActivity : ComponentActivity(), SwipeListener, ClickListener {
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
                        listener = this,
                        clickListener = this,
                        contex = applicationContext,
                        updateUiService = updateUiService,
                        notificationType = notificationType,
                        notificationLogger = notificationLogger
                    )
                }
            }
        }
    }

    override fun onRiteSwipe() {
        Log.d("check", "Я пошел на право!")
    }

    override fun onLeftSwipe() {
        Log.d("check", "А я на лево!")
    }

    override fun onClick() {
        Log.d("check", "Кликнул")
    }

    override fun onLongClick() {
        Log.d("check", "Просто нажал")
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
