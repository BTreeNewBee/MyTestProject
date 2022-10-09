package kt.funs

class Funs1 {

}


//默认参数值
fun add(str: String, default: String = ""): Unit {

}

fun main() {

    var i = 10;
    var age = 10;
    i = when (age) {
        else -> 30
    }

    //只有一个参数可以直接用it
    highLevelFunction(10, { println(it) }) { acc, i -> "$acc $i" }
    val a = A()
    a()
}

//高阶参数传参
fun highLevelFunction(age: Int, funs: (name: String) -> Unit, funs1: (name: String, age: Int) -> String) {
    funs("what")
}


class A {
    operator fun invoke() {

    }
}