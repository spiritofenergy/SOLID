package com.kodex.solid.screens

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import com.kodex.solid.MainActivity
import com.kodex.solid.loger.NotificationLogger
import com.kodex.solid.services.EmailNotification
import com.kodex.solid.services.ToastNotification
import com.kodex.solid.services.UpdateUiService
import kotlin.math.roundToInt

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun MainScreen(

    contex: Context,
    updateUiService: UpdateUiService,
    notificationLogger: NotificationLogger,
    notificationType: MainActivity.NotificationType,

) {
    val textState = remember { mutableStateOf(TextFieldValue()) }
    val width = 96.dp
    val squareSize = 48.dp
    val swipeableState = rememberSwipeableState(initialValue = 0)
    val sizePx = with(LocalDensity.current){ squareSize.toPx()}
    val anchors = mapOf(0f to 0, sizePx to 1)

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
        ) {
        Text(text = "The text field Has text ${updateUiService.updateNotification(textState.value.text)}")
        TextField(value = textState.value, onValueChange = {textState.value = it })

        Box(modifier = Modifier
            .width(width)
            .padding(top = 20.dp)
            .swipeable(
                state = swipeableState,
                anchors = anchors,
                thresholds = { _, _ -> FractionalThreshold(0.3f) },
                orientation = Orientation.Horizontal
            )
            .background(Color.LightGray)
        ){
            Box(
                modifier = Modifier
                .offset { IntOffset(swipeableState.offset.value.roundToInt(), 0)}
                .offset(squareSize)
                .background(Color.DarkGray)
            )

        }
        Button(onClick = {
            when (notificationType) {
                MainActivity.NotificationType.TOAST -> {
                    ToastNotification(contex).sendNotification(textState.value.text)
                    notificationLogger.logNotification(textState.value.text)
                }
                MainActivity.NotificationType.EMAIL -> {
                    EmailNotification().sendNotification(textState.value.text)
                    notificationLogger.logNotification(textState.value.text)
                }
            }
         },
        modifier = Modifier.padding(top = 20.dp)) {
            Text(text = "Send")
        }
    }

}