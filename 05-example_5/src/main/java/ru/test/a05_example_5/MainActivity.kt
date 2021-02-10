package ru.test.a05_example_5

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) = runBlocking<Unit> {
        super.onCreate(savedInstanceState)

        launch { // запустить новую сопрограмму в рамках runBlocking
            delay(1000L)
            Log.d("myLog", "World!")
        }
        Log.d("myLog", "Hello,")
    }
}