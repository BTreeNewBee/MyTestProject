package kt.coroutine.callback

import kotlin.concurrent.thread

fun main() {
    println("a")
    println("b")
}

val task = {
    println("c")
}