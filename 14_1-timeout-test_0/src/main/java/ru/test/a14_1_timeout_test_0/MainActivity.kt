package ru.test.a14_1_timeout_test_0

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.coroutines.*

class MainActivity : AppCompatActivity() {

    lateinit var job: Job

    override fun onCreate(savedInstanceState: Bundle?) = runBlocking {
        super.onCreate(savedInstanceState)

        job = launch {
            try {
                withTimeout(9300L) {
                    repeat(1000) { i ->
                        Log.v("myLog", "job_1: Я сплю $i ...")
                        delay(500L)
                    }
                }
            } catch (exception: TimeoutCancellationException) {
                Log.v("myLog", "job_1: Время вышло!")
            } catch (exception: CancellationException) {
                Log.v("myLog", "job_1: Корутина отменена извне!")
            }
        }

        launch {
            delay(7100L)
            job.cancel()
            Log.v("myLog", "job_0: Отмена действия job_1.")
        }

        try {
            withTimeout(25000L) {
                repeat(1000) { i ->
                    Log.v("myLog", "job_2: Я сплю $i ...")
                    delay(1000L)
                }
            }
        } catch (exception: TimeoutCancellationException) {
            Log.v("myLog", "job_2: Время вышло!")
        } catch (exception: CancellationException) {
            Log.v("myLog", "job_2: Корутина отменена извне!")
        }

        Log.v("myLog", "main: Продолжаем.")
        onDestroy()
    }
}