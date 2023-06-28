package kt.coroutine

import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() = runBlocking {
    repeat(10) {
        launch {
            delay(4000)
            println("World1!")
        }
        launch {
            delay(5000)
            println("World2!")
        }
        println("Hello1,")
    }
    println("Hello2,")
}

