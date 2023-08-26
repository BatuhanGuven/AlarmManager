package com.example.alarmmanager

import android.content.Intent
import android.media.Ringtone
import android.media.RingtoneManager
import android.os.Bundle
import android.widget.Toast
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

lateinit var ringtone: Ringtone

class MainActivity2 : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var alarmUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM)
        ringtone = RingtoneManager.getRingtone(this,alarmUri)
        ringtone.play()
        if (ringtone.isPlaying){
            Toast.makeText(this,"Alarm Çalıyor",Toast.LENGTH_LONG).show()
        }
        setContent {
            MainScreen2()
        }
    }
}

@Composable
fun MainScreen2(){
    val context = LocalContext.current
    Box(modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center){
        Button(onClick = {
            if(ringtone.isPlaying){
                ringtone.stop()
                Intent(context,MainActivity::class.java).also {
                    context.startActivity(it)
                }
            }

        }) {
            Text(text = "Alarm Kapat")
        }
    }

}