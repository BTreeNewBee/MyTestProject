package kt.dataclass

import kotlinx.serialization.*
import kotlinx.serialization.Transient
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.Json.Default.decodeFromString
import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.modules.SerializersModuleBuilder
import kotlin.reflect.KClass


//@Serializable
//data class Project(val name: String, @Transient val language: String = "Kotlin")


fun main() {

    val list = object : ArrayList<String>() {
        internal var a = "1"
        init {
            add(a)
            add("2")
            add2()
        }

        fun add2() {
            add("3")
        }
    }

    println(list.a)
    println(list.add2())
}

fun printRequest(baseRequest: BaseRequest) {
    println(baseRequest)
    println(baseRequest.toJson())
}

fun BaseRequest.toJson(): String = Json.encodeToString(this)

@Serializable
sealed class Content

@Serializable
data class MemberListRequestContent(val target: String) : Content()

@Serializable
sealed class BaseRequest(
    val command: String,
    val content: MemberListRequestContent,
    val subCommand: String,
    val syncId: Int
)

@Serializable
data class MemberListRequest(val target: String) : BaseRequest(
    command = "memberList",
    content = MemberListRequestContent(target),
    subCommand = "",
    syncId = 123
)


//@OptIn(InternalSerializationApi::class)
//private val json by lazy {
//    Json {
//        encodeDefaults = true
//        isLenient = true
//        ignoreUnknownKeys = true
//        @Suppress("UNCHECKED_CAST")
//        serializersModule = SerializersModule {
//            polymorphicSealedClass(DTO::class, MemberListRequest::class)
//        }
//    }
//}



@InternalSerializationApi
@Suppress("UNCHECKED_CAST")
private fun <B : Any, S : B> SerializersModuleBuilder.polymorphicSealedClass(
    baseClass: KClass<B>,
    sealedClass: KClass<S>
) {
    sealedClass.sealedSubclasses.forEach {
        val c = it as KClass<S>
        polymorphic(baseClass, c, c.serializer())
    }
}


