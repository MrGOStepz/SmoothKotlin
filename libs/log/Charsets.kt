package com.smooth.pos.log

import org.apache.logging.log4j.status.StatusLogger
import java.nio.charset.Charset

/**
 * Charset utilities. Contains the standard character sets guaranteed to be available on all implementations of the
 * Java platform. Parts adapted from JDK 1.7 (in particular, the `java.nio.charset.StandardCharsets` class).
 *
 * @see java.nio.charset.Charset
 */
object Charsets {
    /**
     * Seven-bit ASCII. ISO646-US. The Basic Latin block of the Unicode character set.
     */
    val US_ASCII = Charset.forName("US-ASCII")

    /**
     * ISO Latin Alphabet No. 1, a.k.a. ISO-LATIN-1.
     */
    val ISO_8859_1 = Charset.forName("ISO-8859-1")

    /**
     * Eight-bit UCS Transformation Format.
     */
    @JvmField
    val UTF_8 = Charset.forName("UTF-8")

    /**
     * Sixteen-bit UCS Transformation Format, big-endian byte order.
     */
    val UTF_16BE = Charset.forName("UTF-16BE")

    /**
     * Sixteen-bit UCS Transformation Format, little-endian byte order.
     */
    val UTF_16LE = Charset.forName("UTF-16LE")

    /**
     * Sixteen-bit UCS Transformation Format, byte order identified by an optional byte-order mark.
     */
    val UTF_16 = Charset.forName("UTF-16")

    /**
     * Returns a Charset, if possible the Charset for the specified `charsetName`, otherwise (if the specified
     * `charsetName` is `null` or not supported) this method returns the platform default Charset.
     *
     * @param charsetName
     * name of the preferred charset or `null`
     * @return a Charset, not null.
     */
    fun getSupportedCharset(charsetName: String?): Charset? {
        return getSupportedCharset(charsetName, Charset.defaultCharset())
    }

    /**
     * Returns a Charset, if possible the Charset for the specified `charsetName`, otherwise (if the specified
     * `charsetName` is `null` or not supported) this method returns the platform default Charset.
     *
     * @param charsetName
     * name of the preferred charset or `null`
     * @param defaultCharset
     * returned if `charsetName` is null or is not supported.
     * @return a Charset, never null.
     */
    fun getSupportedCharset(charsetName: String?, defaultCharset: Charset?): Charset? {
        var charset: Charset? = null
        if (charsetName != null && Charset.isSupported(charsetName)) {
            charset = Charset.forName(charsetName)
        }
        if (charset == null) {
            charset = defaultCharset
            if (charsetName != null) {
                StatusLogger.getLogger().error(
                    "Charset " + charsetName + " is not supported for layout, using " + charset!!.displayName()
                )
            }
        }
        return charset
    }
}