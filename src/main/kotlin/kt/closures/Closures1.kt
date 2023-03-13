package kt.closures


fun main() {
    //芝士一个class实例化的对象
    val closures1 = funs()
    println(closures1.first())
    println(closures1.first())
    println(closures1.second())
    println(closures1.second())

}


fun funs(): Pair<() -> Int, () -> Int> {
    var i = 0
    return { ++i } to  { --i }
}


