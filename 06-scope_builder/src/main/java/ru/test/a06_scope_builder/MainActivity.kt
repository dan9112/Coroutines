package ru.test.a06_scope_builder

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) = runBlocking<Unit> {
        super.onCreate(savedInstanceState)

        launch {
            delay(200L)
            Log.d("myLog", "Task from runBlocking")
        }

        coroutineScope { // Создает область сопрограммы
            launch {
                delay(500L)
                Log.d("myLog", "Task from nested launch")
            }

            delay(100L)
            Log.d(
                "myLog",
                "Task from coroutine scope"
            ) // Эта строка будет напечатана перед вложенным запуском
        }

        Log.d(
            "myLog",
            "Coroutine scope is over"
        ) // Эта строка не печатается до завершения вложенного запуска
    }
}