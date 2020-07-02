package com.quick.tor.log

import mu.KotlinLogging
import java.lang.Exception

class LoggerImpl(
    private val logger: KotlinLogging
): Logger {
    override fun info(message: String) {
        logger.logger(this.toString()).info(message)
    }

    override fun error(message: String, exception: Exception) {
        logger.logger(this.toString()).catching(exception)
    }

}