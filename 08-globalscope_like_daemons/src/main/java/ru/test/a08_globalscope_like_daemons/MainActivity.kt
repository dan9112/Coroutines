package ru.test.a08_globalscope_like_daemons

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import kotlinx.coroutines.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) = runBlocking {
        super.onCreate(savedInstanceState)

        launch {
            repeat(1000) { i ->
                Log.d("myLog", "I'm sleeping $i ...")
                delay(100L)
            }
        }

        delay(2000L) // просто выходим после задержки
        onDestroy()
    }
}