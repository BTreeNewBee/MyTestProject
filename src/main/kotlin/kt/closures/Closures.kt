package kt.closures


fun main() {
    //芝士一个class实例化的对象
    val closures1 = Closures()
    //芝士一个函数
    val closures2 = closuresFun()
    //芝士一个函数
    val closures3 = Closures2()
    println(closures1())
    println(closures1())
    println(closures1())

    println(closures2())
    println(closures2())
    println(closures2())

    println(closures3())
    println(closures3())
    println(closures3())
}

//假闭包
class Closures {
    private var i = 0
    operator fun invoke() = i++
}

class Closures2 : () -> Int {
    private var i = 0
    override fun invoke(): Int = i++
}

//真闭包
fun closuresFun(): () -> Int {
    var i = 0
    return { i++ }
}

//?
fun closuresWhat() = Closures()
