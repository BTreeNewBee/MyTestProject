package kt.coroutine.callback

import kotlin.coroutines.Continuation
import kotlin.coroutines.EmptyCoroutineContext
import kotlin.coroutines.createCoroutine
import kotlin.coroutines.resume

fun main() {

    val createCoroutine = suspend {
        println("Hello World")
    }.createCoroutine(object : Continuation<Unit> {

        override fun resumeWith(result: Result<Unit>) {
            println("Coroutine completed")
        }

        override val context: EmptyCoroutineContext
            get() = EmptyCoroutineContext
    })

    createCoroutine.resume(Unit)
}