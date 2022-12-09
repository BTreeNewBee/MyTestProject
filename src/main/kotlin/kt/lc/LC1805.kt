package kt.lc


fun main() {
    println(test("abc123abc00123abcde1"))
}

fun test(s: String) = "\\d+".toRegex()
    .findAll(s)
    .map { it.value.toInt() }
    .distinct()
    .count()

fun test1(s: String): Any = {
    s.toCharArray().forEachIndexed { index, c ->

    }

}