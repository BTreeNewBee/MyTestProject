package kt.dates

import java.time.LocalDate

fun main() {
    var of = LocalDate.of(1970, 1, 1)
    val arrayList = ArrayList<LocalDate>()
    for (i in 1..100) {
        of = of.plusYears(1)
        arrayList.add(of)
    }
    arrayList.groupBy { it.isLeapYear }.forEach { (t, u) ->
        run {
            println(if (t) "闰年" else "平年")
            u.groupBy { it.dayOfWeek.value }
                .forEach { (t, u) -> println("今年第一天是星期$t : ${u.map { it.year }}") }
        }
    }

}