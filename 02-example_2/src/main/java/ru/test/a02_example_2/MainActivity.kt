package ru.test.a02_example_2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        GlobalScope.launch { // запустить новую сопрограмму в фоновом режиме и продолжить
            delay(1000L)
            Log.d("myLog", "World!")
        }
        Log.d("myLog", "Hello,") // основной поток продолжается здесь немедленно
        runBlocking {     // но это выражение блокирует основной поток
            delay(2000L)  // ... пока мы задерживаемся на 2 секунды, чтобы поддерживать "жизнь" JVM
        }
    }
}