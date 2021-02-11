package ru.test.a16_2_sequential

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.coroutines.*
import kotlin.system.measureTimeMillis

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) = runBlocking<Unit> {
        super.onCreate(savedInstanceState)

        val time = measureTimeMillis {
            val one = doSomethingUsefulOne()
            val two = doSomethingUsefulTwo()
            Log.v("myLog", "Ответ: ${one + two}")
        }
        Log.v("myLog", "Завершено за $time мс")
    }

    private suspend fun doSomethingUsefulOne(): Int {
        delay(1000L)
        return 13
    }

    private suspend fun doSomethingUsefulTwo(): Int {
        delay(1000L)
        return 29
    }
}