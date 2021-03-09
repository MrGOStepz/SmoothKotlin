package com.smooth.pos.log

import org.apache.logging.log4j.web.Log4jServletContextListener
import javax.servlet.ServletContextEvent
import javax.servlet.ServletContext
import com.smooth.pos.log.Log4jConfigurationAppenderServletContextListener
import org.apache.logging.log4j.web.Log4jWebSupport
import java.util.regex.Pattern

class Log4jConfigurationAppenderServletContextListener : Log4jServletContextListener() {
    override fun contextInitialized(event: ServletContextEvent) {
        val conx = event.servletContext
        val relativeLogPath = conx.getInitParameter(LOG4J_CUSTOM_PARAMETER_NAME)
        val realPath = conx.getRealPath(relativeLogPath)
        val realPathFixSlash = WINDOWS_DIRECTORY_SEPARATOR.matcher(realPath).replaceAll("/")
        val realPathWithProtocol = FILE_PROTOCOL_PREFIX + realPathFixSlash
        conx.setInitParameter(Log4jWebSupport.LOG4J_CONFIG_LOCATION, realPathWithProtocol)
        super.contextInitialized(event)
    }

    companion object {
        private val WINDOWS_DIRECTORY_SEPARATOR = Pattern.compile("\\\\+")
        private const val LOG4J_CUSTOM_PARAMETER_NAME = "log4jConfigurationRelativeWebRootPath"
        private const val FILE_PROTOCOL_PREFIX = "file:///"
    }
}