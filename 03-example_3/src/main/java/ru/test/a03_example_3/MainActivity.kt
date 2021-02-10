package ru.test.a03_example_3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) =
        runBlocking { // запускаем основную сопрограмму
            super.onCreate(savedInstanceState)

            GlobalScope.launch { // запустить новую сопрограмму в фоновом режиме и продолжить
                delay(1000L)
                Log.d("myLog", "World!")
            }
            Log.d("myLog", "Hello,") // основная сопрограмма продолжается здесь немедленно
            delay(2000L) // задержка на 2 секунды, чтобы JVM работала
        }
}