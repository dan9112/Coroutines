package ru.test.a09_1_cancelling_coroutine_execution

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.coroutines.cancelAndJoin
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) = runBlocking<Unit> {
        super.onCreate(savedInstanceState)

        val job = launch {
            repeat(1000) { i ->
                Log.d("myLog", "job: Я сплю $i ...")
                delay(500L)
            }
        }
        delay(1300L) // небольшая задержка
        Log.d("myLog", "main: Я устал ждать!")
        job.cancel() // отмена job
        job.join() // ожидание завершения job
        Log.d("myLog", "main: Сейчас я могу прекратить.")
        onDestroy()
    }
}