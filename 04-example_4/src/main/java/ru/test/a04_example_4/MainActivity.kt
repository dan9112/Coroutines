package ru.test.a04_example_4

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

            val job =
                GlobalScope.launch { // запустить новую сопрограмму и сохранить ссылку на ее задание
                    delay(1000L)
                    Log.d("myLog", "World!")
                }
            Log.d("myLog", "Hello,")
            job.join() // ожидание завершения дочерней сопрограммы
        }
}