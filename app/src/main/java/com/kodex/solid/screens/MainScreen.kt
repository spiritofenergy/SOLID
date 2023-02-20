@file:OptIn(ExperimentalFoundationApi::class)

package com.kodex.solid.screens

import android.content.Context
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.combinedClickable
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
import com.kodex.solid.listener.ClickListener
import com.kodex.solid.listener.SwipeListener
import com.kodex.solid.loger.NotificationLogger
import com.kodex.solid.services.EmailNotification
import com.kodex.solid.services.ToastNotification
import com.kodex.solid.services.UpdateUiService
import com.kodex.solid.ui.theme.SOLIDTheme
import kotlin.math.roundToInt


@ExperimentalMaterialApi
@ExperimentalMaterial3Api
@Composable
fun MainScreen(

    contex: Context,
    listener: SwipeListener,
    clickListener: ClickListener,
    updateUiService: UpdateUiService,
    notificationLogger: NotificationLogger,
    notificationType: MainActivity.NotificationType,

    ) {
    val textState = remember { mutableStateOf(TextFieldValue()) }
    val width = 96.dp
    val squareSize = 48.dp
    val swipeableState = rememberSwipeableState(initialValue = 0)
    val sizePx = with(LocalDensity.current) { squareSize.toPx() }
    val anchors = mapOf(0f to 0, sizePx to 1)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .fillMaxWidth()
            .padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "The text field Has text: ${
                updateUiService.updateNotification(textState.value.text)
            }",
        modifier = Modifier
            .combinedClickable (
                onClick = {},
                onLongClick = {clickListener.onLongClick()}
                    ))

        TextField(
            value = textState.value,
            onValueChange = { textState.value = it })
        Box(
            modifier = Modifier
                .width(width)
                .padding(top = 20.dp)
                .swipeable(
                    state = swipeableState,
                    anchors = anchors,
                    thresholds = { _, _ -> FractionalThreshold(0.3f) },
                    orientation = Orientation.Horizontal
                )
                .background(Color.LightGray)
        ) {
            when(swipeableState.currentValue){
            1-> listener.onRiteSwipe()
            0-> listener.onLeftSwipe()
        }
            Box(
                modifier = Modifier
                    .width(width)
                    .offset {
                        IntOffset(swipeableState.offset.value.roundToInt(), 0) }
                    .size(squareSize)
                    .background(Color.DarkGray)
            ) {

            }
        }

        Button(
            onClick = {
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
            modifier = Modifier.padding(top = 20.dp)
        ) {
            Text(text = "Send")
        }
    }


}

annotation class ExperimentalMaterial3Api

@OptIn(ExperimentalMaterialApi::class)
@Composable

fun ShowMainScreen(
    contex: Context,
    updateUiService: UpdateUiService,
    listener: SwipeListener,
    clickListener: ClickListener,
    notificationLogger: NotificationLogger,
    notificationType: MainActivity.NotificationType){
    SOLIDTheme{
    MainScreen(
        contex = contex,
        updateUiService = updateUiService,
        listener = listener as MainActivity,
        notificationLogger = notificationLogger,
        notificationType = notificationType,
        clickListener = clickListener)
}
}