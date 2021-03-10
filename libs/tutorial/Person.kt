//class Person(_firstName:String, _lastName:String) {
//    val firstName: String = _firstName
//    val lastName: String = _lastName
//
//}

//class Person(val firstName:String,val lastName:String) {
//
//    init {
//        println("init1")
//    }
//    constructor(): this("Peter","Parker") {
//        println("Secondary constructor last Run")
//    }
//    init {
//        println("init2")
//    }
//}

class Person(val firstName:String = "Peter",val lastName:String = "Parker") {
    var nickName: String? = null
        set(value) {
            field = value
            println("the new nick name is $value")
        }
        get() {
            println("the returned value is $field")
            return field
        }

    fun printInfo() {
//        val nickNameToPrint = if(nickName != null) nickName else "no nickname"
        val nickNameToPrint = nickName ?: "no Nick name"
        println("$firstName ($nickNameToPrint) $lastName")
    }
}