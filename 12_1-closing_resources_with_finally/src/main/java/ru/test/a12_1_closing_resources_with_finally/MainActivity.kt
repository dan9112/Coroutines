package ru.test.a12_1_closing_resources_with_finally

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.coroutines.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) = runBlocking {
        super.onCreate(savedInstanceState)

        val job = launch {
            try {
                repeat(1000) { i ->
                    Log.d("myLog", "job: Я сплю $i ...")
                    delay(500L)
                }
            } finally {
                Log.d("myLog", "job: Я наконец работаю")
            }
        }
        delay(1300L) // небольшая задержка
        Log.d("myLog", "main: Я устал ждать!")
        job.cancelAndJoin() // отмена задания и ожидание его завершения
        Log.d("myLog", "main: Сейчас я могу прекратить.")
        onDestroy()
    }
}