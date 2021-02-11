package ru.test.a15_1_asynchronous_timeout_and_resources

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.coroutines.*

class MainActivity : AppCompatActivity() {

    companion object {
        var acquired = 0
    }

    class Resource {
        init {
            acquired++
        } // Получение ресурса

        fun close() {
            acquired--
        } // Высвобождение ресурса
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        runBlocking {
            repeat(100_000) { // Запуск 100K сопрограмм
                launch {
                    var resource: Resource? = null // Пока не получен
                    try {
                        withTimeout(60) { // Тайм-аут 60 мс
                            delay(50) // Ожидание 50 мс
                            resource =
                                Resource() // Сохранение ресурса в переменной, если он был получен
                        }
                        // Здесь возможно что-нибудь сделать с ресурсом
                    } finally {
                        resource?.close() // Высвобождение ресурса, если он был получен
                    }
                }
            }
        }
        // Вне runBlocking все сопрограммы завершены
        Log.v("myLog", acquired.toString()) // Вывод количества полученных ресурсов
        onDestroy()
    }
}