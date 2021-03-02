//fun printFilteredString(list: List<String>,predicate: (String) -> Boolean) {
//    list.forEach {
//        if(predicate(it)) {
//            println(it)
//        }
//    }
//}

fun printFilteredString(list: List<String>,predicate: ((String) -> Boolean)?) {
    list.forEach {
        if(predicate?.invoke(it) == true) {
            println(it)
        }
    }
}

val predicate: (String) -> Boolean = {
    it.startsWith("J")
}

fun getPrintPredicate(): (String) -> Boolean {
    return { it.startsWith("J")}
}

fun main() {
    val list = listOf("Kotlin", "Java", "C++", "JavaScript")
    printFilteredString(list) {
        it.startsWith("J")
    }

    printFilteredString(list,null)
    printFilteredString(list,predicate)
    printFilteredString(list,getPrintPredicate())
}