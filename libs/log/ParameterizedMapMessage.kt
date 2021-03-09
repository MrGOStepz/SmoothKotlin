package com.smooth.pos.log

import org.apache.logging.log4j.message.ParameterizedMessage
import java.util.SortedMap
import java.util.TreeMap
import com.smooth.pos.log.ParameterizedMapMessage
import java.math.BigDecimal

class ParameterizedMapMessage : ParameterizedMessage {
    val data: SortedMap<String, Any> = TreeMap()

    /**
     * Constructor
     */
    constructor(messagePattern: String?, arg: String?) : super(messagePattern, arg) {}
    constructor(messagePattern: String?, arg1: String?, arg2: String?) : super(messagePattern, arg1, arg2) {}
    constructor(messagePattern: String?, arguments: Array<String?>) : super(messagePattern, *arguments) {}
    constructor(message: String?) : super(message, null) {}

    fun getData(): Map<String, Any> {
        return data
    }

    fun addKeyValue(key: String, value: String): ParameterizedMapMessage {
        data[key] = value
        return this
    }

    fun addKeyValue(key: String, value: Int): ParameterizedMapMessage {
        data[key] = value
        return this
    }

    fun addKeyValue(key: String, value: Long): ParameterizedMapMessage {
        data[key] = value
        return this
    }

    fun addKeyValue(key: String, value: Float): ParameterizedMapMessage {
        data[key] = value
        return this
    }

    fun addKeyValue(key: String, value: Double): ParameterizedMapMessage {
        data[key] = value
        return this
    }

    fun addKeyValue(key: String, value: BigDecimal): ParameterizedMapMessage {
        data[key] = value
        return this
    }

    companion object {
        private const val serialVersionUID = 1L
    }
}