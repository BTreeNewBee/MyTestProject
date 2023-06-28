package kt.coroutine

import kotlinx.coroutines.*

val myScope1 = CoroutineScope(Dispatchers.Default)
val myScope2 = CoroutineScope(Dispatchers.Default)
val myScope3 = MainScope()

fun main() {

    println()

    println("yes, it is a coroutine")




    runBlocking {
        test()
        println("Hello2,")
    }


}

suspend fun test() {
    println("Hello,")
    delay(2000)
    println("World!")
    coroutineScope {
        println("coroutineScope1!")
        delay(2000)
        println("coroutineScope2!")
    }
    println("test finish!")
}

