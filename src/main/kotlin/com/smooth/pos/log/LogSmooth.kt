package com.smooth.pos.log

import java.util.*

class LogSmooth {
    companion object {
        fun message(): String? {
            var logMessage: String? = ""
            data.forEach { (t, u) ->
                logMessage += "$t : $u "
            }
            return logMessage
        }

        private var data: SortedMap<String, Any> = TreeMap()

        fun getData(): Map<String, Any>? {
            return data
        }

        fun addKeyValue(key: String, value: String) {
            data[key] = value
        }

        fun addKeyValue(key: String, value: Int) {
            data[key] = value
        }

        fun addKeyValue(key: String, value: Long) {
            data[key] = value
        }

        fun addKeyValue(key: String, value: Float) {
            data[key] = value
        }

        fun addKeyValue(key: String, value: Double) {
            data[key] = value
        }

        fun getMessage(): String? {
            this.data = TreeMap()
            return this.message()
        }
    }
}