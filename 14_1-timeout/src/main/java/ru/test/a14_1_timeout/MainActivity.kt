package ru.test.a14_1_timeout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.coroutines.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) = runBlocking {
        super.onCreate(savedInstanceState)

        try {
            withTimeout(1300L) {
                repeat(1000) { i ->
                    Log.d("myLog", "Я сплю $i ...")
                    delay(500L)
                }
            }
        } catch (exception: TimeoutCancellationException) {
            Log.d("myLog", "Время вышло!")
        } catch (exception: CancellationException) {
            Log.d("myLog", "Корутина отменена из-вне!")
        }
        onDestroy()
    }
}