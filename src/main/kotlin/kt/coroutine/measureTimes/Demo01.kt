package kt.coroutine.measureTimes

import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import kotlin.system.measureTimeMillis

fun main() = runBlocking {
    measureTimeMillis {
        doHello()
        doWorld()
    }.also { println("Completed in $it ms") }
    println("Hello2,")
}

suspend fun doHello(): Int {
    delay(1000L)
    return 20
}


suspend fun doWorld(): Int {
    delay(1000L)
    return 30
}
