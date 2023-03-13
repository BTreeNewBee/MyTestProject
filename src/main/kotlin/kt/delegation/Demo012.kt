package kt.delegation

fun main() {
    Derived(BaseImpl(10),Base2Impl(20))
}


interface Base {
    fun a()
}


class BaseImpl(val x: Int) : Base {
    override fun a() {
        println("function a$x")
    }
}

interface Base2 {
    fun b()
}


class Base2Impl(val x: Int) : Base2 {
    override fun b() {
        println(x)
    }
}

class Derived(base: Base, base2: Base2) : Base by base, Base2 by base2
