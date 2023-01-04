package kt.maps


fun main() {

    sortedMapOf("c" to 1, "b" to 2, "a" to 3)
        .map { "${it.key}=${it.value}" }
        .joinToString("&")
        .let(::println)

}