package kt.inlines


fun main() {

    //sampleStart
    val helloWorld = object {
        val hello = "Hello"
        val world = "World"
        // object expressions extend Any, so `override` is required on `toString()`
        override fun toString() = "$hello $world"
    }
//sampleEnd
    print(helloWorld)

}

@JvmInline
value class Password(private val s: String) {
    init {
        require(s.length >= 8) { "Password must be at least 8 characters" }
    }
}