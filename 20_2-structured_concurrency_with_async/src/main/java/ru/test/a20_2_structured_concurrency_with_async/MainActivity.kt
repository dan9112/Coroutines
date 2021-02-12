package ru.test.a20_2_structured_concurrency_with_async

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import kotlin.system.measureTimeMillis

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) = runBlocking<Unit> {
        super.onCreate(savedInstanceState)

        try {
            failedConcurrentSum()
        } catch (e: ArithmeticException) {
            Log.v("myLog", "Ошибка вычислений: ArithmeticException")
        }
    }

    suspend fun failedConcurrentSum(): Int = coroutineScope {
        val one = async<Int> {
            try {
                delay(Long.MAX_VALUE) // Подражает очень долгим вычислениям
                42
            } finally {
                Log.v("myLog", "Первое порождение отменено")
            }
        }
        val two = async<Int> {
            Log.v("myLog", "Второе порождение выдаёт исключение")
            throw ArithmeticException()
        }
        one.await() + two.await()
    }
}