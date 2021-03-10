
val firstName: String = "FirstName"


//Null
val nullValue: String? = null

//Not initial type
val notType = "Test"

fun main() {
    //Val Cannot Update Variable
//    val const: String = "Name"
//
//    var name: String = "Name"
//    name = "Name something"
//    println(name)
//
//    greeting = "Hi, "
//    println(firstName)

//    ifTest()
//    whenTest()
//    println(getGreeting())
//    sayHello()
//    sayHello4("MrGO","Something")
//    arrayTest()
//    listTest()

//    val interestingThings = arrayOf("Kotlin", "Programing", "Comic Books")
//    sayHello6("Hi", "Test", "Test2", "Test3")
//    sayHello6("Hi", *interestingThings)

//    sayHello4(name = "Hi", name2 =  "Go")
//    sayHello4(name = "Hi")

//    val interestingThings = arrayOf("Kotlin", "Programing", "Comic Books")
//    sayHello6(name  = "Hi", name2 =  *interestingThings)

    val person = Person()
//    person.firstName
//    person.lastName
//    person.nickName = "Shades"
//    person.nickName = "New Nickname"
//    println(person.nickName)
    person.printInfo()

}

fun ifTest() {
//    var greeting: String = "Hi"
    var greeting: String? = null


    if(greeting != null) {
        println(greeting)
    } else {
        println("Is null value")
    }

    val greetingToPrint = if(greeting != null) greeting else "Hi"
    println("greetingToPrint $greetingToPrint")
}

fun whenTest() {
//    var greeting: String? = "HellO world"
    var greeting: String? = null

    when (greeting) {
        null -> println("Hi")
        else -> println(greeting)
    }

    val greetingToPrint = when (greeting) {
        null -> println("Hi")
        else -> println(greeting)
    }
}

fun getGreeting(): String? {
    return "Hello Kotlin"
}

fun getGreeting2(): String ="Hello Kotlin"
fun getGreeting3()  = "Hello Kotlin"

fun sayHello():Unit {
    println(getGreeting())
}

fun sayHello2() {
    println(getGreeting())
}

fun sayHello3(name: String) {
    val msg = "Hello $name"
    println(msg)
}

fun sayHello4(name: String = "Hello", name2:String = "Kotlin") = println("Hello $name $name2")

fun sayHello5(name: String, name2:List<String>) {
    name2.forEach { name22 ->
        println("$name $name22")
    }
}

fun sayHello6(name: String, vararg name2: String) {
    name2.forEach { name22 ->
        println("$name $name22")
    }
}

fun arrayTest() {
    val interestingThings = arrayOf("Kotlin", "Programing", "Comic Books")
    println(interestingThings.size)
    println(interestingThings[0])
    println(interestingThings.get(0))

//    for(interestingThing in interestingThings) {
//        println(interestingThing)
//    }

//    interestingThings.forEach {
//        println(it)
//    }

//    interestingThings.forEach { interestingThing ->
//        println(interestingThing)
//    }

    interestingThings.forEachIndexed { index, intersingThing ->
        println("$intersingThing is at index $index")
    }
}

fun listTest() {
//    val interestingThings = listOf("Kotlin", "Programing", "Comic Books")
    val interestingThings = mutableListOf("Kotlin", "Programing", "Comic Books")
    interestingThings.add("Dog")
//    interestingThings.forEach { interestingThing ->
//        println(interestingThing)
//    }
//    val map = mapOf(1 to "a", 2 to "b", 3 to "c")
    val map = mutableMapOf(1 to "a", 2 to "b", 3 to "c")
    map.forEach { key, value -> println("$key -> $value") }
    map.put(4, "d")

    sayHello5("Hi", interestingThings)
}
