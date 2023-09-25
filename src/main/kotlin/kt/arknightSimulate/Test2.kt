@file:Suppress("UNREACHABLE_CODE")

package kt.arknightSimulate

import java.util.*


//招聘单价
val array1 = arrayOf(
    140, 143, 147, 150, 154, 157,
    161, 164, 168, 171, 175, 178,
    182, 185, 189, 192, 196, 199,
    203, 245, 252, 259, 266, 273,
    280, 287, 294, 301, 308, 315,
    322, 329, 336, 343, 350, 357,
    364, 371, 378, 385, 490, 504,
    518, 532, 546, 560, 574, 588,
    602
)


data class Recruit(val hour: Int, val minute: Int, val tags: Int, val price: Int)


fun main() {
    val mutableListOf = mutableListOf<Recruit>()
    var minute = 60
    array1.forEach {
        mutableListOf.add(Recruit(minute / 60, minute % 60, 0, it))
        minute += 10
    }

    val mutableListOf2 = mutableListOf<Recruit>()
    mutableListOf.forEach { recruit ->
        repeat(4) { tag ->
            mutableListOf2.add(Recruit(recruit.hour, recruit.minute, tag, recruit.price + tag * 70))
        }
    }
    mutableListOf2.forEach {
//        println("公招${it.hour}小时${it.minute}分钟，选${it.tags}个tag需要${it.price}龙门币")
    }
    val intRecruitMap = mutableListOf2.associateBy { it.price }
    val array = mutableListOf2.map { it.price }.sorted().toIntArray()

    val money = 999

    val result = coinChange(array, money, IntArray(money), Array(money) { emptyList() })
    if (result.first == -1) {
        println("无法凑出${money}龙门币")
    }
    println("希望把${money}龙门币刚刚好花完，最少需要${result.first}次公招，分别是以下配置")
    result.second.map { intRecruitMap[it] }.forEach {
        if (it != null) {
            println("公招${it.hour}小时${it.minute}分钟，选${it.tags}个tag需要${it.price}龙门币")
        }
    }


}


fun coinChange(coins: IntArray, rem: Int, count: IntArray, list: Array<List<Int>>): Pair<Int, List<Int>> {
    if (rem < 0) {
        return -1 to emptyList()
    }
    if (rem == 0) {
        return 0 to emptyList()
    }
    if (count[rem - 1] != 0) {
        return count[rem - 1] to list[rem - 1]
    }
    var min = Int.MAX_VALUE
    var minList = emptyList<Int>()
    for (coin in coins) {
        val res = coinChange(coins, rem - coin, count, list)
        if (res.first in 0 until min) {
            min = 1 + res.first
            minList = res.second + coin
        }
    }
    count[rem - 1] = if (min == Int.MAX_VALUE) -1 else min
    list[rem - 1] = if (min == Int.MAX_VALUE) emptyList() else minList
    return count[rem - 1] to list[rem - 1]
}

