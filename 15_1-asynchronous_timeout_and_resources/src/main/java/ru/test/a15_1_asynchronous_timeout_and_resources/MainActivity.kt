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
        } // Acquire the resource

        fun close() {
            acquired--
        } // Release the resource
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        runBlocking {
            repeat(100_000) { // Launch 100K coroutines
                launch {
                    var resource: Resource? = null // Not acquired yet
                    try {
                        withTimeout(5) { // Timeout of 60 ms
                            delay(10) // Delay for 50 ms
                            resource =
                                Resource() // Store a resource to the variable if acquired
                        }
                        // We can do something else with the resource here
                    } finally {
                        resource?.close() // Release the resource if it was acquired
                    }
                }
            }
        }
        // Outside of runBlocking all coroutines have completed
        Log.v("myLog", acquired.toString()) // Print the number of resources still acquired
        onDestroy()
    }
}