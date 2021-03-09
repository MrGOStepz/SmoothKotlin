package com.smooth.pos.log

import org.apache.logging.log4j.core.layout.AbstractStringLayout
import com.fasterxml.jackson.core.JsonFactory
import org.apache.logging.log4j.core.LogEvent
import java.io.StringWriter
import com.fasterxml.jackson.core.JsonGenerator
import com.fasterxml.jackson.core.util.MinimalPrettyPrinter
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter
import com.smooth.pos.log.StandardField
import com.smooth.pos.log.JSONEventLayoutLog4j2
import java.io.IOException
import kotlin.Throws
import com.smooth.pos.log.ParameterizedMapMessage
import org.apache.commons.lang3.time.FastDateFormat
import org.apache.logging.log4j.Logger
import org.apache.logging.log4j.message.MapMessage
import java.io.PrintWriter
import java.util.TreeSet
import org.apache.logging.log4j.ThreadContext.ContextStack
import org.apache.logging.log4j.core.Layout
import org.apache.logging.log4j.core.config.Node
import org.apache.logging.log4j.core.config.plugins.Plugin
import java.math.BigDecimal
import java.lang.StackTraceElement
import org.apache.logging.log4j.status.StatusLogger
import java.util.TimeZone
import org.apache.logging.log4j.core.config.plugins.PluginFactory
import org.apache.logging.log4j.core.config.plugins.PluginAttribute
import org.apache.logging.log4j.core.config.plugins.PluginElement
import org.apache.logging.log4j.core.util.KeyValuePair
import org.apache.logging.log4j.util.Strings
import java.lang.StringBuilder
import java.lang.management.ManagementFactory
import java.nio.charset.Charset
import java.util.HashMap

@Plugin(name = "JSONEventLayoutLog4j2", category = Node.CATEGORY, elementType = Layout.ELEMENT_TYPE, printObject = true)
class JSONEventLayoutLog4j2(
    dateFormat: String?, locationInfo: Boolean, properties: Boolean, compact: Boolean,
    additionalLogAttributes: Map<String?, String?>?, charset: Charset?
) : AbstractStringLayout(charset) {
    val noStackTraceLine = 3
    private val dateFormat: FastDateFormat
    private var locationInfo = false
    private var properties = false
    private var compact = false
    private val additionalLogAttributes: Map<String?, String?>?
    private val jsonFactory: JsonFactory
    override fun toSerializable(logEvent: LogEvent): String {
        return try {
            //create fasterxml JSON Generator
            val strWriter = StringWriter() //finally get string result from this
            val jg = jsonFactory.createGenerator(strWriter) //write what ever you want using this
            if (compact) {
                jg.prettyPrinter = MinimalPrettyPrinter()
            } else {
                jg.prettyPrinter = DefaultPrettyPrinter()
            }
            jg.writeStartObject()
            /************************************************
             * Write basic field
             */
            jg.writeStringField(StandardField.TIMESTAMP, dateFormat(logEvent.timeMillis))
            jg.writeStringField(StandardField.LOGGER, logEvent.loggerName)
            jg.writeStringField(StandardField.SEVERITY, logEvent.level.toString())
            jg.writeStringField(StandardField.THREAD_NAME, logEvent.threadName)
            jg.writeStringField(StandardField.PROCESS_ID, pID)
            /**************************************************
             * Write value that derive from Log4j2 thread local.
             */
            if (properties) {
                //jg.writeObjectField("mdc", logEvent.getContextMap());
                writeMDC(logEvent, jg)
                writeNDC(logEvent, jg)
            }
            /**************************************************
             * Extract and add fields from log4j configuration, if defined
             */
            if (additionalLogAttributes != null && additionalLogAttributes.size != 0) {
                for ((key, value) in additionalLogAttributes) {
                    jg.writeStringField(key, value)
                }
            }
            /**************************************************
             * Write message
             */
            writeMessage(logEvent, jg)
            /**************************************************
             * write value that derive from Thread.currentThread().getStackTrace()
             */
            if (locationInfo) {
                writeLocationInfo(logEvent, jg)
            }
            /**************************************************
             * finally write exception stacktrace
             */
            writeThrowable(logEvent, jg)
            jg.writeEndObject()
            jg.close()
            strWriter.append(DEFAULT_EOL)
            strWriter.toString()
        } catch (e: IOException) {
            LOGGER.error("Failed to write event as JSON", e)
            Strings.EMPTY
        }
    }

    /**
     * For detect null
     */
    @Throws(IOException::class)
    private fun addEventData(keyname: String, keyval: String?, jg: JsonGenerator) {
        if (null != keyval) {
            jg.writeStringField(keyname, keyval)
        }
    }

    fun dateFormat(timestamp: Long): String {
        return dateFormat.format(timestamp)
    }

    @Throws(IOException::class)
    private fun writeMessage(logEvent: LogEvent, jg: JsonGenerator) {
        /**
         * Now we start injecting Message
         *
         * in case of message that contain map,
         * as we tend to not write complex JSON object. each map in message need to be outer attribute.
         */
        val message = logEvent.message
        if (message is ParameterizedMapMessage) {
            jg.writeStringField(StandardField.MESSAGE, logEvent.message.formattedMessage)
            val map = message.data
            for ((key, value) in map) {
                writeJsonKeyValue(jg, key, value)
            }
        } else if (message is MapMessage<*, *>) {
            val map: Map<String, String> = message.getData() as Map<String, String>
            for ((key, value) in map) {
                jg.writeStringField(key, value)
            }
        } else {
            jg.writeStringField(StandardField.MESSAGE, logEvent.message.formattedMessage)
        }
    }

    @Throws(IOException::class)
    private fun writeThrowable(logEvent: LogEvent, jg: JsonGenerator) {
        //Note logEvent.getThrownProxy() will return more detail
        if (logEvent.thrown != null) {
            val throwable = logEvent.thrown
            if (throwable.javaClass.simpleName != null) {
                val exceptionClassSimpleName = throwable.javaClass.simpleName
                jg.writeStringField(StandardField.EXCEPTION_CLASS, exceptionClassSimpleName)
            }
            if (throwable.message != null) {
                jg.writeStringField(StandardField.EXCEPTION_MESSAGE, throwable.message)
            }
            var throwStackTrace: String? = null

            //shorten stack trace if there is no more cause object.
            throwStackTrace = if (throwable.cause == null) {
                val builder = StringBuilder()
                /**
                 * Normally, the first line of stack trace contains the result of the toString() method for this object.
                 * Remaining lines represent data previously recorded by the method fillInStackTrace().
                 */
                builder.append(throwable.toString())
                var index = 0
                while (index < noStackTraceLine && index < throwable.stackTrace.size) {
                    builder.append(DEFAULT_STACKTRACE_EOL).append(throwable.stackTrace[index])
                    index++
                }
                builder.toString()
            } else {
                val sw = StringWriter()
                val pw = PrintWriter(sw)
                throwable.printStackTrace(pw)
                sw.toString()
            }
            if (!throwStackTrace.isEmpty()) {
                //String stackTrace = StringUtils.join(throwable.getStackTrace(), DEFAULT_EOL);   //TODO: get cause by error
                jg.writeStringField(StandardField.STACK_TRACE, throwStackTrace)
            }
        }
    }

    @Throws(IOException::class)
    private fun writeMDC(logEvent: LogEvent, jg: JsonGenerator) {
        val mdc = logEvent.contextMap
        if (mdc == null || mdc.isEmpty()) return
        val keys: Set<String> = TreeSet(mdc.keys)
        for (key in keys) {
            jg.writeStringField(key, mdc[key])
        }
    }

    @Throws(IOException::class)
    private fun writeNDC(logEvent: LogEvent, jg: JsonGenerator) {
        val ndc = logEvent.contextStack
        if (ndc == null || ndc.depth == 0) return
        val ndcList = ndc.asList()
        jg.writeArrayFieldStart("ndc")
        for (stackElement in ndcList) {
            jg.writeString(stackElement)
        }
        jg.writeEndArray()
    }

    @Throws(IOException::class)
    private fun writeJsonKeyValue(jg: JsonGenerator, fieldName: String, value: Any) {
        if (value is String) {
            jg.writeStringField(fieldName, value)
        } else if (value is Int) {
            jg.writeNumberField(fieldName, value)
        } else if (value is Double) {
            jg.writeNumberField(fieldName, value)
        } else if (value is Long) {
            jg.writeNumberField(fieldName, value)
        } else if (value is Float) {
            jg.writeNumberField(fieldName, value)
        } else if (value is BigDecimal) {
            jg.writeNumberField(fieldName, value)
        } else {
            jg.writeStringField(fieldName, value.toString())
        }
    }

    @Throws(IOException::class)
    private fun writeStringMap(mapName: String, stringMap: Map<String, String>?, jg: JsonGenerator) {
        if (stringMap == null || stringMap.isEmpty()) return
        jg.writeFieldName(mapName)
        jg.writeStartObject()

        // TreeSet orders fields alphabetically by key:
        val keys: Set<String> = TreeSet(stringMap.keys)
        for (key in keys) {
            jg.writeStringField(key, stringMap[key])
        }
        jg.writeEndObject()
    }

    @Throws(IOException::class)
    private fun writeLocationInfo(logEvent: LogEvent, jg: JsonGenerator) {
        val info = logEvent.source
        if (info != null) {
            val fullClassName = info.className
            val simpleClassName = getSimpleClassName(fullClassName)
            addEventData(StandardField.CLASS_NAME, simpleClassName, jg)
            addEventData(StandardField.METHOD, info.methodName, jg)
        }
    }

    private val pID: String
        private get() {
            val processName = ManagementFactory.getRuntimeMXBean().name
            return processName.split("@").toTypedArray()[0]
        }

    private fun getSimpleClassName(fullClassName: String): String {
        val simpleClassName: String
        val lastDot = fullClassName.lastIndexOf(".")
        simpleClassName = if (lastDot == -1) {
            fullClassName
        } else {
            fullClassName.substring(lastDot + 1)
        }
        return simpleClassName
    }

    companion object {
        private const val serialVersionUID = -5924419853090062697L
        private val LOGGER: Logger = StatusLogger.getLogger()

        /**
         * Default timezone is UTC
         */
        val DEFAULT_TIMEZONE = TimeZone.getTimeZone("UTC")

        /**
         * Default timestamp pattern yyyy-MM-dd'T'HH:mm:ss.SSS'Z'
         */
        const val DEFAULT_DATE_PATTERN = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'"
        const val DEFAULT_CHARSET = "UTF-8"
        const val TAB = "\t"
        val DEFAULT_EOL = System.getProperty("line.separator")
        val DEFAULT_STACKTRACE_EOL = DEFAULT_EOL + TAB

        /**
         * Creates a JSON Layout.
         *
         * @param locationInfo If "true", includes the location information in the generated JSON.
         * @param properties If "true", includes the thread context in the generated JSON.
         * @param compact If "true", does not use end-of-lines and indentation, defaults to "false".
         * @param charset The character set to use, if `null`, uses "UTF-8".
         * @param pairs the additional configuration key-value pair from log4j configuration file.
         * @return A JSON Layout.
         */
        @PluginFactory
        fun createLayout( // @formatter:off
            @PluginAttribute(value = "dateFormat", defaultString = DEFAULT_DATE_PATTERN) dateFormat: String?,
            @PluginAttribute(value = "locationInfo", defaultBoolean = false) locationInfo: Boolean,
            @PluginAttribute(value = "properties", defaultBoolean = false) properties: Boolean,
            @PluginAttribute(value = "compact", defaultBoolean = false) compact: Boolean,
            @PluginAttribute(value = "charset", defaultString = DEFAULT_CHARSET) charset: Charset?,
            @PluginElement("Pairs") pairs: Array<KeyValuePair>? // @formatter:on
        ): JSONEventLayoutLog4j2 {

            //Unpack the pairs list
            var additionalLogAttributes: MutableMap<String?, String?>? = null
            if (pairs != null && pairs.size > 0) {
                additionalLogAttributes = HashMap((pairs.size / 0.75).toInt() + 1)
                for (pair in pairs) {
                    val key = pair.key
                    if (key == null) {
                        LOGGER.error("A null key is not valid in MapFilter")
                        continue
                    }
                    val value = pair.value
                    if (value == null) {
                        LOGGER.error("A null value for key $key is not allowed in MapFilter")
                        continue
                    }
                    if (additionalLogAttributes.containsKey(key)) {
                        LOGGER.error("Duplicate entry for key: {} is forbidden!", key)
                    }
                    additionalLogAttributes[key] = value
                }
            }
            return JSONEventLayoutLog4j2(
                dateFormat,
                locationInfo,
                properties,
                compact,
                additionalLogAttributes,
                charset
            )
        }

        /**
         * Creates a JSON Layout using the default settings.
         *
         * @return A JSON Layout.
         */
        fun createDefaultLayout(): JSONEventLayoutLog4j2 {
            return JSONEventLayoutLog4j2(DEFAULT_DATE_PATTERN, false, false, false, null, Charsets.UTF_8)
        }
    }

    /**
     * Creates a layout that optionally inserts location information into log messages.
     *
     * @param locationInfo whether or not to include location information in the log messages.
     */
    init {
        LOGGER.entry()
        this.dateFormat = FastDateFormat.getInstance(dateFormat, DEFAULT_TIMEZONE)
        this.locationInfo = locationInfo
        this.properties = properties
        this.compact = compact
        this.additionalLogAttributes = additionalLogAttributes
        jsonFactory = JsonFactory()
        LOGGER.exit()
    }
}