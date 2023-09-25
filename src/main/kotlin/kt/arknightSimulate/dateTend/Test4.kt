@file:Suppress("UNREACHABLE_CODE")

package kt.arknightSimulate.dateTend

import cn.hutool.core.io.FileUtil
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.decodeToSequence
import kt.arknightSimulate.Chars
import kt.arknightSimulate.MaaCopilotFile
import kt.arknightSimulate.OperatorInfo
import kt.arknightSimulate.getNewFilesByDay
import org.eclipse.jgit.api.Git
import java.io.File
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*
import kotlin.collections.ArrayList


@OptIn(ExperimentalSerializationApi::class)
fun main() {
    val file = FileUtil.file("C:\\git\\maa-copilot-store")
    val it = FileUtil.file("C:\\git\\Untitled-3.json")
    //decode to sequence
    val charsList = json.decodeToSequence<Chars>(it.inputStream())
    val charsMap = charsList.associateBy { it.name }

    val repository = Git.open(file).repository
    val git = Git(repository)
    val newFilesByDay = getNewFilesByDay(git)

    processFile(file, newFilesByDay)


    //按每周日期汇总
    //从2023-03-06开始，每周汇总
    //作业总数
    val weekWorkCount = mutableMapOf<String, Int>()
    //干员出场人次
    val weekCount = mutableMapOf<String, Int>()
    //每个干员的出场次数
    val weekCountOpers = mutableMapOf<String, MutableMap<String, Int>>()

    var start = LocalDate.of(2023, 8, 28)
    while (start.isBefore(LocalDate.now())) {
        val dateKey = start.toString()
        for (i in 0..6) {
            val date = start.format(DateTimeFormatter.ISO_LOCAL_DATE)
            start = start.plusDays(1)
            val count = count[date] ?: 0
            val operatorCount = operatorCount[date] ?: 0
            val mutableMap = operatorMap[date] ?: continue
            weekWorkCount[dateKey] = weekWorkCount[dateKey]?.plus(count) ?: count
            weekCount[dateKey] = weekCount[dateKey]?.plus(operatorCount) ?: operatorCount
            val operMap = weekCountOpers.getOrPut(dateKey) { mutableMapOf<String, Int>() }
            mutableMap.forEach { (key, value) ->
                operMap[key] = operMap[key]?.plus(value) ?: value
            }
        }
    }


    val reversedMap = mutableMapOf<String, MutableMap<String, Int>>()



    for ((date, personCounts) in operatorMap) {
        //过滤掉start之前的日期，脏数据
        if (LocalDate.parse(date) < start) {
            continue
        }
        for ((person, count) in personCounts) {
            val datePersonCounts = reversedMap.getOrPut(person) { mutableMapOf() }
            datePersonCounts[date] = count
        }
    }


    val skipDate = LocalDate.of(2023, 8, 28)
    val windowSize = 7  // 滑动窗口大小为7天
    val outputList = mutableListOf<String>()


    val dataWindowCount = mutableMapOf<String, MutableMap<String, Int>>()

    for ((person, dateCounts) in reversedMap) {
        var totalPersonCounts = 0
        var currentDate = skipDate
        while (currentDate < LocalDate.now()) {
            val date = currentDate.format(DateTimeFormatter.ISO_LOCAL_DATE)
            val count = dateCounts[date] ?: 0
            totalPersonCounts += count
            dateCounts[currentDate.minusDays(windowSize.toLong()).format(DateTimeFormatter.ISO_LOCAL_DATE)]?.let {
                totalPersonCounts -= it
            }
            val output = "$person,$date,$totalPersonCounts"
            outputList.add(output)
            dataWindowCount.getOrPut(person) { mutableMapOf() }[date] = totalPersonCounts
            currentDate = currentDate.plusDays(1)
        }
    }

//
//    //再次翻转结构
//    val reversed2Map = mutableMapOf<String, MutableMap<String, Int>>()
//    for ((person, personCounts) in dataWindowCount) {
//        for ((date, count) in personCounts) {
//            val datePersonCounts = reversed2Map.getOrPut(date) { mutableMapOf() }
//            datePersonCounts[person] = count
//        }
//    }
//
//    //reversed2Map每天取前30条数据
//    val dataWindowCountTop30 = mutableMapOf<String, MutableMap<String, Int>>()
//    for ((date, personCounts) in reversed2Map) {
//        val list = ArrayList<Map.Entry<String, Int>>(personCounts.entries)
//        list.sortByDescending { it.value }
//        val subList = list.subList(0, if (list.size > 30) 30 else list.size)
//        val mutableMap = mutableMapOf<String, Int>()
//        subList.forEach {
//            mutableMap[it.key] = it.value
//        }
//        dataWindowCountTop30[date] = mutableMap
//    }
//
//    //dataWindowCountTop30转换为"$person,$date,$totalPersonCounts"格式输出
//    for ((date, personCounts) in dataWindowCountTop30) {
//        for ((person, count) in personCounts) {
//            val output = "$person,$date,$count"
//            outputList.add(output)
//        }
//    }

//    outputList.sort()

// 输出结果
    //dataWindowCount每天只取前30条数据
//    val dataWindowCountTop30 = mutableMapOf<String, MutableMap<String, Int>>()
//    for ((person, dateCounts) in dataWindowCount) {
//        val list = ArrayList<Map.Entry<String, Int>>(dateCounts.entries)
//        list.sortByDescending { it.value }
//        val subList = list.subList(0, if (list.size > 30) 30 else list.size)
//        val mutableMap = mutableMapOf<String, Int>()
//        subList.forEach {
//            mutableMap[it.key] = it.value
//        }
//        dataWindowCountTop30[person] = mutableMap
//    }
//
//    //dataWindowCountTop30转换为"$person,$date,$totalPersonCounts"格式输出
//    for ((person, dateCounts) in dataWindowCountTop30) {
//        for ((date, count) in dateCounts) {
//            val output = "$person,$date,$count"
//            outputList.add(output)
//        }
//    }

    //输出
//
//    for (output in outputList) {
//        println(output)
//    }


//    //打印输出
//    weekWorkCount.forEach { (key, value) ->
//        println(key)
//        println("作业总数$value")
//        println("干员出场人次${weekCount[key]}")
//        weekCountOpers[key]?.let {
//            val list = ArrayList<Map.Entry<String, Int>>(it.entries)
//            list.sortByDescending { it.value }
//            val subList = list.subList(0, if (list.size > 50) 50 else list.size)
//            subList.forEachIndexed { index, it ->
//                println(
//                    "${index + 1},${it.key},${it.value},${String.format("%.0f", it.value * 100.0 / value)}%"
//                )
//            }
//        }
//    }


    //横向打印
    weekWorkCount.forEach { (key, value) ->
        print("日期,$key")
        print(",,")
    }
    println()
    weekWorkCount.forEach { (key, value) ->
        print("作业数量,$value")
        print(",,")
    }
    println()
    weekCount.forEach { (t, u) ->
        print("干员出场人次,$u")
        print(",,")
    }
    println()
    for (i in 0 .. 50) {
        weekWorkCount.forEach { (key, value) ->
            weekCountOpers[key]?.let {
                val list = ArrayList<Map.Entry<String, Int>>(it.entries)
                list.sortByDescending { it.value }
                val get = list[i]
                print("${get.key},${get.value},${String.format("%.0f", get.value * 100.0 / value)}%,")
            }
        }
        println()
    }


    //map sort by value
    //从2023-05-01开始向后循环
//    LocalDate.of(2023, 8, 1).datesUntil(LocalDate.of(2023, 8, 21)).forEach {
//        val dateKey = it.toString()
//        val count = count[dateKey] ?: 0
//        val operatorCount = operatorCount[dateKey] ?: 0
//        println("${dateKey}作业总数$count")
//        println("${dateKey}干员出场人次$operatorCount")
//        val mutableMap = operatorMap[dateKey] ?: return@forEach
//        val list = ArrayList<Map.Entry<String, OperatorInfo>>(mutableMap.entries)
//        list.sortByDescending { it.value.useCount }
//        val subList = list.subList(0, if (list.size>50) 50 else list.size)
//
//        subList.forEachIndexed { index, it ->
//            val chars = charsMap[it.key]
//            //println("排名${index + 1}:${it.key},作业出场次数${it.value.useCount},作业出场率 ${String.format("%.2f", it.value.useCount * 100.0 / count)}%,技能使用率${String.format("%.0f", it.value.skillUseCount[0] * 100.0/it.value.useCount)}%,${String.format("%.0f", it.value.skillUseCount[1] * 100.0/it.value.useCount)}%,${String.format("%.0f", it.value.skillUseCount[2] * 100.0/it.value.useCount)}%")
//
//            println(
//                "${index + 1},${it.key},${it.value.useCount}, ${
//                    String.format(
//                        "%.2f",
//                        it.value.useCount * 100.0 / count
//                    )
//                }%,${String.format("%.0f", it.value.skillUseCount[0] * 100.0 / it.value.useCount)}%,${
//                    String.format(
//                        "%.0f",
//                        it.value.skillUseCount[1] * 100.0 / it.value.useCount
//                    )
//                }%,${String.format("%.0f", it.value.skillUseCount[2] * 100.0 / it.value.useCount)}%"
//            )
//        }
//
//    }


//    top = subList.map { it.key }.toSet()
//    filterFile(file)
//    println(
//        "在练了前${subList.size}个干员的情况下可以抄${
//            String.format(
//                "%.2f",
//                (allowCount * 100.0 / count)
//            )
//        }%的作业,覆盖${String.format("%.2f", (allowActionCount.size * 100.0 / actions.size))}%的关卡"
//    )


}


var top: Set<String> = setOf()

//每个干员的使用次数
val operatorMap = mutableMapOf<String, MutableMap<String, Int>>()

//作业总数
var count = mutableMapOf<String, Int>()

//使用干员总数
var operatorCount = mutableMapOf<String, Int>()


//关卡总数
var actions = mutableSetOf<String>()

//可抄关卡总数
var allowActionCount = mutableSetOf<String>()

//可抄作业总数
var allowCount = 0

fun processFile(file: File, dateMap: Map<String, String>) {
    if (file.isFile) {
        countOperator(file, dateMap)
    }
    if (file.isDirectory) {
        file.listFiles()?.forEach {
            processFile(it, dateMap)
        }
    }
}

private val json = Json {
    ignoreUnknownKeys = true
    isLenient = true
    encodeDefaults = true
    allowStructuredMapKeys = true
    prettyPrint = true
    useArrayPolymorphism = true
    classDiscriminator = "type"
}

//计算干员出现次数
fun countOperator(file: File, dateMap: Map<String, String>) {
    if (file.extension != "json") {
        return
    }
    val name = file.name
    val date = dateMap[name]

    if (date == null) {
        println("date is null whit file ${file.absoluteFile}")
        return
    }

    val readText = file.readText()
    val maaCopilotFile = json.decodeFromString(MaaCopilotFile.serializer(), readText)
    maaCopilotFile.opers.forEach { it ->
        val orDefault = operatorMap.getOrPut(date) { mutableMapOf() }
        orDefault[it.name] = orDefault.getOrDefault(it.name, 0) + 1
        operatorCount[date]?.let { count ->
            operatorCount[date] = count + 1
        } ?: operatorCount.put(date, 1)
    }
    maaCopilotFile.groups?.forEach { group ->
        group.opers.forEach {
            val orDefault = operatorMap.getOrPut(date) { mutableMapOf() }
            orDefault[it.name] = orDefault.getOrDefault(it.name, 0) + 1
            //计数
            operatorCount[date]?.let { count ->
                operatorCount[date] = count + 1
            } ?: operatorCount.put(date, 1)
        }
    }
    //按日期计数
    count[date]?.let { c ->
        count[date] = c + 1
    } ?: count.put(date, 1)
}


fun filterFile(file: File) {
    if (file.isFile) {
        filterOperator(file)
    }
    if (file.isDirectory) {
        file.listFiles()?.forEach {
            filterFile(it)
        }
    }
}

fun filterOperator(file: File) {
    if (file.extension != "json") {
        return
    }
    val readText = file.readText()
    val maaCopilotFile = json.decodeFromString(MaaCopilotFile.serializer(), readText)
    actions.add(maaCopilotFile.stageName)
    maaCopilotFile.opers.filter { !top.contains(it.name) }.let {
        if (it.isEmpty()) {
            allowCount++
            allowActionCount.add(maaCopilotFile.stageName)
        }
    }
}


data class OperatorInfo(
    var name: String = "",
    var useCount: Int = 0,
    val skillUseCount: Array<Int> = arrayOf(0, 0, 0)
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as OperatorInfo

        if (name != other.name) return false
        if (useCount != other.useCount) return false
        if (!skillUseCount.contentEquals(other.skillUseCount)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = name.hashCode()
        result = 31 * result + useCount
        result = 31 * result + skillUseCount.contentHashCode()
        return result
    }
}

