package kt.lc

fun main() {
    println(checkIfPangram("thequickbrownfoxjumpsoverthelazydog"))
}

fun checkIfPangram(sentence: String): Boolean {
//    return sentence.toCharArray().distinct().count() == 26
    val bytes = sentence.toByteArray()
    var mask = 0
    for (aByte in bytes) {
        mask = mask or (1 shl aByte.toInt() - 0x61)
    }
    return mask == 0x3ffffff
}