import java.util.*

import java.util.*

enum class EntityType {
    HELP, EASY, MEDIUM, HARD;

    fun getFormatterName() = name.toLowerCase().capitalize()
}
//interface IdProvider {
//    fun getId(): String
//}

//object EntityFactory {
//    fun create() = Entity("id","name")
//}

object EntityFactory {
    fun create(type: EntityType) :Entity {
        val id = UUID.randomUUID().toString()
        val name = when (type) {
            EntityType.EASY -> type.name
            EntityType.MEDIUM -> type.getFormatterName()
            EntityType.HARD -> "Hard"
            EntityType.HELP -> type.getFormatterName()
        }
//        return Entity(id, name)
        return when (type) {
            EntityType.EASY -> Entity.Easy(id,name)
            EntityType.MEDIUM -> Entity.Medium(id,name)
            EntityType.HARD -> Entity.Hard(id,name, 2f)
            EntityType.HELP -> Entity.Help
        }
    }
}


//class Entity private constructor(val id: String){
//class Entity constructor(val id: String){
//    companion object Factory : IdProvider{
//        override fun getId(): String {
//            return "123"
//        }
//
//        const val id = "id"
//        fun create() = Entity(id)
//    }

//    companion object Factory{
//        fun create() = Entity("id")
//    }
//class  Entity(val id:String, val name: String) {
//    override fun toString(): String {
//        return "id:$id name:$name"
//    }
//}

//class  Entity(val id:String, val name: String) {
//    override fun toString(): String {
//        return "id:$id name:$name"
//    }
//}

sealed class Entity() {
    object Help : Entity() {
        val name = "Help"
    }


//    data class Easy(val id:String, val name:String):Entity() {
//        override fun equals(other: Any?): Boolean {
//            return super.equals(other)
//        }
//
//        override fun hashCode(): Int {
//            return super.hashCode()
//        }
//    }
    data class Easy(val id:String, val name:String):Entity()
    data class Medium(val id:String, val name:String):Entity()
    data class Hard(val id:String, val name:String, val multiplier: Float):Entity()
}

fun Entity.Medium.printInfo() {
    println("Medium class: $id")
}

val Entity.Medium.info: String
    get() = "some info"

fun main() {
//    val entity = Entity("id")
//    val entity = EntityFactory.create(EntityType.EASY)
//    println(entity)
//
//    val mediumEntity = EntityFactory.create(EntityType.MEDIUM)
//    println(mediumEntity)

//    val entity = EntityFactory.create(EntityType.EASY)
//    val msg = when (entity) {
//        Entity.Help -> "Help Class"
//        is Entity.Easy -> "Easy Class"
//        is Entity.Hard -> "Hard Class"
//        is Entity.Medium -> "Medium Class"
//    }
//
//    println(msg)

//    val entity1 = EntityFactory.create(EntityType.EASY)
//    val entity2 = EntityFactory.create(EntityType.EASY)

//    val entity1 = Entity.Easy("id","name")
//    val entity2 = Entity.Easy("id","name")

    //Equal
//    val entity1 = Entity.Easy("id","name")
//    val entity2 = entity1ame

    //They are not equal
//    val entity3 = entity1.copy(name = "new name")
//
//    if (entity1 === entity2) {
//        println("They are equal")
//    } else {
//        println("They are not equal")
//    }

    Entity.Medium("id","name").printInfo()

    val entity1 = Entity.Easy("id","name")
    val entity2 = EntityFactory.create(EntityType.MEDIUM)

    if (entity2 is Entity.Medium) {
        entity2.printInfo()
        entity2.info
    }

}
