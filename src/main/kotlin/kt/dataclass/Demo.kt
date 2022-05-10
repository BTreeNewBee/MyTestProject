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
//    val data = Json.decodeFromString<Project>("""
//        {"name":"kotlinx.serialization","language":"Kotlin"}
//    """)
//    println(data)


    val memberListRequest = MemberListRequest("1017809249")
    printRequest(memberListRequest)

//    val baseRequest = BaseRequest("memberList", MemberListRequestContent("1017809249"),"",123)
//    println(baseRequest)
//    BaseRequest(command=memberList, content=MemberListRequestContent(target=1017809249), subCommand=, syncId=123)
//    println(baseRequest.toJson())
    //{"command":"memberList","content":{},"subCommand":"","syncId":123}
}

fun printRequest(baseRequest: BaseRequest) {
    println(baseRequest)
    println(baseRequest.toJson())
}

fun BaseRequest.toJson(): String = Json.encodeToString(this)
@Serializable
sealed class Content
@Serializable
data class MemberListRequestContent (val target:String) : Content()
@Serializable
sealed class BaseRequest (
    val command: String,
    val content: MemberListRequestContent,
    val subCommand: String,
    val syncId: Int
)
@Serializable
data class MemberListRequest (val target: String) : BaseRequest(
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


