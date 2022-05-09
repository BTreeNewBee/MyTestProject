package kt.dataclass

import kotlinx.serialization.InternalSerializationApi
import kotlinx.serialization.Serializable
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.modules.SerializersModuleBuilder
import kotlinx.serialization.serializer
import kotlin.reflect.KClass

fun main() {
    val memberListRequest = MemberListRequest("1017809249")
    println(memberListRequest)
    println(memberListRequest.toJson())
//    val baseRequest = BaseRequest("memberList", MemberListRequestContent("1017809249"),"",123)
//    println(baseRequest)
//    println(baseRequest.toJson())
}


@Serializable
open class BaseRequest (
     val command: String,
     val content: Content,
     val subCommand: String,
     val syncId: Int
): DTO

 interface DTO


fun BaseRequest.toJson(): String = json.encodeToString(this)

@Serializable
open class Content

@OptIn(InternalSerializationApi::class)
private val json by lazy {
    Json {
        encodeDefaults = true
        isLenient = true
        ignoreUnknownKeys = true
        @Suppress("UNCHECKED_CAST")
        serializersModule = SerializersModule {
            polymorphicSealedClass(DTO::class, MemberListRequest::class)
        }
    }
}



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


@Serializable
data class MemberListRequest (val target: String) : BaseRequest(
    command = "memberList",
    content = MemberListRequestContent(target),
    subCommand = "",
    syncId = 123
)

@Serializable
data class MemberListRequestContent (val target:String) : Content()