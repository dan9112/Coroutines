package ru.test.a01_start

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        GlobalScope.launch { // запустить новую сопрограмму в фоновом режиме и продолжить
            delay(1000L) // неблокирующая задержка на 1 секунду (единица времени по умолчанию - мс)
            Log.d("myLog", "World!") // добавить в строку после задержки
        }
        Log.d("myLog", "Hello,") // основной поток продолжается, пока сопрограмма задерживается
        Thread.sleep(2000L) // заблокировать основной поток на 2 секунды, чтобы JVM оставалась в живых
    }
}