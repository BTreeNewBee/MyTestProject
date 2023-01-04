package kt.coroutine

import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking

fun main() = runBlocking {
    println("Hello,")
    delay(1000L)
    println("World!")
}