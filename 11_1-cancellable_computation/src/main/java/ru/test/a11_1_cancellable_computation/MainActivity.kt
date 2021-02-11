package ru.test.a11_1_cancellable_computation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.coroutines.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) = runBlocking {
        super.onCreate(savedInstanceState)

        val startTime = System.currentTimeMillis()
        val job = launch(Dispatchers.Default) {
            var nextPrintTime = startTime
            var i = 0
            while (isActive) { // отменяемый вычислительный цикл
                // вывод сообщения дважды в секунду
                if (System.currentTimeMillis() >= nextPrintTime) {
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