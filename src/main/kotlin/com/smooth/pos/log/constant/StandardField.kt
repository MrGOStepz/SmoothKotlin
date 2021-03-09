package com.smooth.pos.log.constant

object StandardField {
    /**
     * This should use ISO-8601 format timestamps with as much precision as possible, and at least millisecond.
     */
    const val TIMESTAMP = "timestamp"

    /**
     * The 'Business Process ID' , which is similar the the Request ID, but is generated at the client, and is carried with the request end-to-end.
     */
    const val BUSINESS_PROCESS_ID = "BusProcId"

    /**
     * For access logs, this contains the name or ID of the client application that is accessing this system.
     */
    const val CLIENT_APP_ID = "ClientAppId"

    /**
     * Logical component name, usually the application name, that is generating the log.  E.g. "Cache-Ingestor"
     * This helps select log entries where multiple applications are generating logs on a given machine.
     */
    const val COMPONENT = "Component"

    /**
     * The name of the class name that originated the event. Make this meaningful within a 'Component' (see above).
     * Try to avoid using fully qualified class names as these can be unnecessarily long - usually just the last segment is significant.
     */
    const val CLASS_NAME = "ClassName"

    /**
     * Prefer to use standard values [INFO, NOTICE, WARNING, ERROR, CRITICAL, DEBUG]
     * Use 'Severity' instead of 'level'.
     */
    const val SEVERITY = "Severity"

    /**
     * unique id for that message type (unique for that message type for that component)
     * (e.g. A message "Unknown Symbol" might be assigned an id of 'B2004')
     */
    const val MESSAGE_ID = "MessageId"

    /**
     * A textual description of the event. It should start with the most important and recognisable information first.
     * This may be followed by extra values that were not considered appropriate to be parsed out into separate ElasticSearch fields.
     */
    const val MESSAGE = "message"
    const val PROCESS_ID = "ProcessId"

    /**
     * Details of the item being requested, e.g. a chunk URL.  See also 'Symbol'; use Symbol wherever relevant.
     */
    const val REQUEST = "Request"

    /**
     * Unique ID for current client request.
     */
    const val REQUEST_ID = "RequestId"

    /**
     * The total response latency caused internal processing and waiting for upstream services. (Unit = milliseconds)
     */
    const val RESPONSE_LATENCY = "ResponseLatency"

    /**
     * RIC or comma separated list of RICs/etc. This can also store other symbologies, such as perm ids.
     * [ML: Do we need another "symbology" field to distinguish different symbology types?]
     */
    const val SYMBOL = "Symbol"

    /**
     * Can be 'PermID', 'TAG' or 'RIC'.  However, 'RIC' is the default and should be omitted.
     * (It is assumed that all symbols in a request use the same symbology.)
     */
    const val SYMBOLOGY = "Symbology"
    const val THREAD_ID = "ThreadId"

    /**
     * Logical thread name/role
     */
    const val THREAD_NAME = "ThreadName"

    /**
     * The response latency caused by waiting for upstream services. (Unit = milliseconds)
     */
    const val UPSTREAM_RESPONSE_LATENCY = "UpstreamResponseLatency"
    const val LOGGER = "Logger"
    const val EXCEPTION_CLASS = "ExceptionClass"
    const val EXCEPTION_MESSAGE = "ExceptionMessage"
    const val STACK_TRACE = "StackTrace"
    const val RIC_TYPE_SYMBOLOGY = "RIC"
    const val PERMID_TYPE_SYMBOLOGY = "PermID"
    const val TAG_TYPE_SYMBOLOGY = "TAG"
    const val METHOD = "Method"
}