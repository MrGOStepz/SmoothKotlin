fun main() {
    val list = listOf("Kotlin", "Java", "C++", "JavaScript",null, null)
//    list
//        .filterNotNull()
//        .filter {
//            it.startsWith("J")
//        }
//        .map {
//            it.length
//        }
//        .forEach {
//            println(it)
//        }
//    list.filterNotNull()
//        .takeLast(3)
//        .forEach {
//            println(it)
//        }

//    list.filterNotNull()
//        .associate { it to it.length }
//        .forEach {
//            println("${it.value}, ${it.key}")
//        }

    val map = list
        .associate { it to it?.length }
        .forEach {
            println("${it.value}, ${it.key}")
        }

//    val language = list.first()
//    val language = list.last()
//    val language = list.filterNotNull().last()
//    val language = list.filterNotNull().find { it.startsWith("J")}
//    val language = list.filterNotNull().findLast { it.startsWith("Java")}
        val language = list.filterNotNull().findLast { it.startsWith("foo")}.orEmpty()
//    println(language)

}