package ru.test.a14_1_timeout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.coroutines.*

class MainActivity : AppCompatActivity() {

    lateinit var jobScope: CoroutineScope

    override fun onCreate(savedInstanceState: Bundle?) = runBlocking {
        super.onCreate(savedInstanceState)

        jobScope = CoroutineScope(coroutineContext)

        launch {
            delay(7100L)
            jobScope.coroutineContext.cancel()
            Log.v("myLog", "job_0: Отмена области действий jobScope.")
        }

        try {
            jobScope.coroutineContext.let {
                withTimeout(9300L) {
                    repeat(1000) { i ->
                        Log.v("myLog", "job_1: Я сплю $i ...")
                        delay(500L)
                    }
                }
            }
        } catch (exception: TimeoutCancellationException) {
            Log.v("myLog", "job_1: Время вышло!")
        } catch (exception: CancellationException) {
            Log.v("myLog", "job_1: Корутина отменена извне!")
        }
        onDestroy()
    }
}