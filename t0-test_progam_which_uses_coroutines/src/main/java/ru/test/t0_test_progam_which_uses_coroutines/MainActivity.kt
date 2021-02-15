package ru.test.t0_test_progam_which_uses_coroutines

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.*

class MainActivity : AppCompatActivity() {
    private lateinit var time: TextView
    private lateinit var rw0: RecyclerView
    private lateinit var btnMain: Button

    private lateinit var adapter: MyRecyclerViewAdapter
    private lateinit var list: ArrayList<String>

    private lateinit var stopwatch: Job

    private var isActive = false // флаг активности вычислителя
    private var isStopped = false // флаг остановки вычислителя
    private var counter: Long = 0L // счётчик вычислителя

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        time = findViewById(R.id.tvDisplay)
        rw0 = findViewById(R.id.rwPoints)
        btnMain = findViewById(R.id.btnStartReset)

        adapter = MyRecyclerViewAdapter(ArrayList())
        rw0.layoutManager = LinearLayoutManager(this)
        rw0.adapter = adapter

        list = arrayListOf()
    }

    override fun onResume() {
        super.onResume()

        adapter.updateAdapter(list)
    }

    fun onClickMain(view: View) {
        isActive = !isActive
        when (isActive) {
            false -> { // Вычилитель запущен
                btnMain.text = "Запустить"
                stopwatch.cancel()
                counter = 0L
            }
            true -> { // Вычислитель не запущен
                btnMain.text = "Сбросить"
                list.clear()
                adapter.updateAdapter(list)
                stopwatch = start()
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()

        stopwatch.cancel()
    }

    private fun formattedCounter(): String {
        var sCounter = counter.toString()
        while (sCounter.length < 12) sCounter = "0" + sCounter
        return sCounter.chunked(3).joinToString(":")
    }

    fun start(): Job {
        return GlobalScope.launch(Dispatchers.Default) {
            while (isActive) {
                counter++
                time.text = counter.toString() // formattedCounter() - не работает форматированный вывод
                if (counter == Long.MAX_VALUE) {
                    counter = 0L
                    showToast("Таймер достиг макимума - ${Long.MAX_VALUE}")
                }
            }
        }
    }

    private fun showToast(string: String) {
        Toast.makeText(this, string, Toast.LENGTH_SHORT).show()
    }

    fun onClickAdd(view: View) {

        list.add(formattedCounter())
        adapter.updateAdapter(list)
    }

    // не работает!
    fun onClickStop(view: View) {
        if (isStopped) stopwatch.cancel() // как остановить корутину без отмены?
        else stopwatch = start()
        isStopped = !isStopped
    }

    fun onClickSave(view: View) {

    }
}
