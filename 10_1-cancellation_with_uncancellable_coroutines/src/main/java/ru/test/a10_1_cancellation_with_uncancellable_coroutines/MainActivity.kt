package ru.test.a10_1_cancellation_with_uncancellable_coroutines

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.coroutines.*
import java.lang.System.currentTimeMillis

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) = runBlocking {
        super.onCreate(savedInstanceState)

        val startTime = currentTimeMillis()
        val job = launch(Dispatchers.Default) {
            var nextPrintTime = startTime
            var i = 0
            while (i < 5) { // цикл вычислений, просто расходует CPU
                // вывод сообщения дважды в секунду
                if (currentTimeMillis() >= nextPrintTime) {
                    Log.d("myLog", "job: Я сплю ${i++} ...")
                    nextPrintTime += 500L
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