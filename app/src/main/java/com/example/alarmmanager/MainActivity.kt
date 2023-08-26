package com.example.alarmmanager

import android.app.AlarmManager
import android.app.PendingIntent
import android.app.TimePickerDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import java.util.Calendar

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainScreen()
        }
    }
}


@Composable
fun MainScreen(){
    val context = LocalContext.current
    var takvim = Calendar.getInstance()
    var onTimeSetListener = TimePickerDialog.OnTimeSetListener{
            view, hourOfDay, minute ->
        val secilen = Calendar.getInstance()
        secilen[Calendar.HOUR_OF_DAY] = hourOfDay
        secilen[Calendar.MINUTE] = minute
        val intent = Intent(context,AlarmReceiver::class.java)
        val pendingIntent = PendingIntent.getBroadcast(context,1,intent,
            PendingIntent.FLAG_IMMUTABLE)
        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        alarmManager[AlarmManager.RTC_WAKEUP,secilen.timeInMillis]=pendingIntent
    }
    Box(modifier =Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center){
        Button(onClick = {
           TimePickerDialog(context, onTimeSetListener,takvim[Calendar.HOUR_OF_DAY],takvim[Calendar.MINUTE],true).show()
        }) {
            Text(text = "Alarm Kur")
        }
    }


}


