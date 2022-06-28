package kt.maps

import java.util.concurrent.ConcurrentHashMap

fun main() {
     var instancePool: ConcurrentHashMap<String, Int> = ConcurrentHashMap()
    val toList = instancePool.values.map { e -> e.toString() }.toList()
    println(toList)

}