package ru.test.a19_2_async_style_functions

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.coroutines.*
import kotlin.system.measureTimeMillis

class MainActivity : AppCompatActivity() {
    private val scope = MainScope() // область действия MyUIClass

    // обратите внимание, что в этом примере у нас нет runBlocking справа от main
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val time = measureTimeMillis {
            // можно инициировать асинхронные действия вне сопрограммы
            val one = somethingUsefulOneAsync()
            val two = somethingUsefulTwoAsync()
            // но ожидание результата должно включать либо приостановку, либо блокировку.
            // здесь используется `runBlocking {...}` для блокировки основного потока в ожидании результата
            runBlocking {
                Log.v("myLog", "Ответ: ${one.await() + two.await()}")
            }
        }
        Log.v("myLog", "Завершено за $time мс")
    }

    private fun somethingUsefulOneAsync() = scope.async {
        doSomethingUsefulOne()
    }

    private fun somethingUsefulTwoAsync() = scope.async {
        doSomethingUsefulTwo()
    }

    private suspend fun doSomethingUsefulOne(): Int {
        delay(1000L)
        return 13
    }

    private suspend fun doSomethingUsefulTwo(): Int {
        delay(1000L)
        return 29
    }

    override fun onDestroy() {
        super.onDestroy()

        scope.cancel()
    }
}