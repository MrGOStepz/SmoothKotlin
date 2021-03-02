//interface PersonInfoPeovider {
//    fun printInfo(person: Person)
//}
//
//class BasicInfoProvider : PersonInfoPeovider {
//    override fun printInfo(person: Person) {
//        print("basicInfoProvider")
//        person.printInfo()
//    }
//}

interface PersonInfoPeovider {
    val providerInfo : String
    fun printInfo(person: Person){
        print(providerInfo)
        person.printInfo()
    }
}

interface SessionInfoProvider {
    fun getSessionId() : String
}

open class BasicInfoProvider : PersonInfoPeovider, SessionInfoProvider {
    override val providerInfo: String
        get() = "BasicInfoProvider"

    protected open val sessionIdPerfix = "Session"

    override fun printInfo(person: Person) {
        super.printInfo(person)
        println("additional print statment")
    }

    override fun getSessionId(): String {
        return sessionIdPerfix
    }
}

fun main() {
//    var provider = BasicInfoProvider()
//    var provider = FancyInfoProvider()
    var provider = object : PersonInfoPeovider {
        override val providerInfo: String
            get() = "New Info Provider"
        fun getSessionId() = "id"
    }

    provider.printInfo(Person())
    provider.getSessionId()

    checkTypes(provider)
}

fun checkTypes(infoPeovider: PersonInfoPeovider) {
//    if(infoPeovider !is SessionInfoProvider) {
    if(infoPeovider is SessionInfoProvider) {
        println("is a session info provider")
//        (infoPeovider as SessionInfoProvider).getSessionId()
        infoPeovider.getSessionId()
    } else {
        println("not a session info provider")
    }
}