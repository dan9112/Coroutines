package ru.test.a13_1_non_cancellable_block

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
                withContext(NonCancellable) {
                    Log.d("myLog", "job: Я наконец работаю")
                    delay(1000L)
                    Log.d(
                        "myLog",
                        "job: И я только что задержался на 1 секунду, потому что меня нельзя отменить"
                    )
                }
            }
        }
        delay(1300L) // небольшая задержка
        Log.d("myLog", "main: Я устал ждать!")
        job.cancelAndJoin() // отмена задания и ожидание его завершения
        Log.d("myLog", "main: Сейчас я могу прекратить.")
        onDestroy()
    }
}