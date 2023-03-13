package kt.coroutine.asyncs

import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import kotlin.system.measureTimeMillis


fun main(): Unit = runBlocking {
    measureTimeMillis {
        val async = async { doHello() }
        val async1 = async { doWorld() }
        println("Hello1, ${async.await() + async1.await()}")
    }.also { println("Completed in $it ms") }
}

suspend fun doHello(): Int {
    delay(1000L)
    return 20
}


suspend fun doWorld(): Int {
    delay(1000L)
    return 30
}


