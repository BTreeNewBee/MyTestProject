package kt.lazys

val name by lazy {
    println("init")
    "龟龟"
}

fun main() {
    println("main")
    println(name)
    println(name)


}