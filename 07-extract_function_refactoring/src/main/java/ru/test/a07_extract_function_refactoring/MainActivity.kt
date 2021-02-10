package ru.test.a07_extract_function_refactoring

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) = runBlocking<Unit> {
        super.onCreate(savedInstanceState)

        launch {
            doWorld()
        }
        Log.d("myLog", "Hello,")
    }

    // это ваша первая функция приостановки
    suspend fun doWorld() {
        delay(1000L)
        Log.d("myLog", "World!")
    }
}