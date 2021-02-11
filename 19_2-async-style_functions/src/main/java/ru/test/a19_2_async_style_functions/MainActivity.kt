package ru.test.a19_2_async_style_functions

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.coroutines.*
import kotlin.system.measureTimeMillis

class MainActivity : AppCompatActivity() {
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
                Log.v("myLog", "The answer is ${one.await() + two.await()}")
            }
        }
        Log.v("myLog", "Completed in $time ms")
    }

    private fun somethingUsefulOneAsync() = GlobalScope.async {
        doSomethingUsefulOne()
    }

    private fun somethingUsefulTwoAsync() = GlobalScope.async {
        doSomethingUsefulTwo()
    }

    private suspend fun doSomethingUsefulOne(): Int {
        delay(1000L) // pretend we are doing something useful here
        return 13
    }

    private suspend fun doSomethingUsefulTwo(): Int {
        delay(1000L) // pretend we are doing something useful here, too
        return 29
    }
}