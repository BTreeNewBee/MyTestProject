@file:Suppress("UNREACHABLE_CODE")

package kt.arknightSimulate

import cn.hutool.core.io.FileUtil
import kotlinx.serialization.json.Json
import java.io.File
import java.util.*
import kotlin.collections.ArrayList


fun main() {
    val file = FileUtil.file("C:\\git\\maa-copilot-store-main")
    processFile(file)
    //map sort by value
    val list = ArrayList<Map.Entry<String, OperatorInfo>>(operatorMap.entries)
    println("作业总数$count")
    println("干员出场人次$operatorCount")
    list.sortByDescending { it.value.useCount }
//    val subList = list.subList(0, 100)
    val subList = list
    subList.forEach {
        println("${it.key} 总出场次数 ${it.value.useCount},作业出场率 ${String.format("%.2f", it.value.useCount * 100.0 / count)}%,技能使用率${String.format("%.2f", it.value.skillUseCount[0] * 100.0/it.value.useCount)},${String.format("%.2f", it.value.skillUseCount[1] * 100.0/it.value.useCount)},${String.format("%.2f", it.value.skillUseCount[2] * 100.0/it.value.useCount)}")
    }
    top = subList.map { it.key }.toSet()
    filterFile(file)
    println(
        "在练了前${subList.size}个干员的情况下可以抄${
            String.format(
                "%.2f",
                (allowCount * 100.0 / count)
            )
        }%的作业,覆盖${String.format("%.2f", (allowActionCount.size * 100.0 / actions.size))}%的关卡"
    )


}


var top: Set<String> = setOf()

val operatorMap = mutableMapOf<String, OperatorInfo>()

//关卡总数
var count = 0

//使用干员总数
var operatorCount = 0


//关卡总数
var actions = mutableSetOf<String>()

//可抄关卡总数
var allowActionCount = mutableSetOf<String>()

//可抄作业总数
var allowCount = 0

fun processFile(file: File) {
    if (file.isFile) {
        countOperator(file)
    }
    if (file.isDirectory) {
        file.listFiles()?.forEach {
            processFile(it)
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
fun countOperator(file: File) {
    if (file.extension != "json") {
        return
    }
    val readText = file.readText()
    val maaCopilotFile = json.decodeFromString(MaaCopilotFile.serializer(), readText)
    maaCopilotFile.opers.forEach {
        val orDefault = operatorMap.getOrDefault(it.name, OperatorInfo(it.name))
        orDefault.useCount ++
        if (it.skill != 0) {
            orDefault.skillUseCount[it.skill - 1]++
        } else {
//            println("作业${file.name}干员${it.name}没有填写使用技能 maa有没有做格式校验啊.jpg")
        }
        operatorMap[it.name] = orDefault
        operatorCount++
    }
    maaCopilotFile.groups?.forEach { group ->
        group.opers.forEach {
            val orDefault = operatorMap.getOrDefault(it.name, OperatorInfo(it.name))
            orDefault.useCount ++
            if (it.skill != 0) {
                orDefault.skillUseCount[it.skill - 1]++
            }
            operatorMap[it.name] = orDefault
            operatorCount++
        }
    }
    count++
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

data class OperatorInfo (
    var name: String = "",
    var useCount: Int = 0,
    val skillUseCount: Array<Int> = arrayOf(0, 0, 0)
)

