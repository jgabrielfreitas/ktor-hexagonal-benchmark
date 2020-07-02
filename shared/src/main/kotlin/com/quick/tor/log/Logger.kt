package com.quick.tor.log

import java.lang.Exception

interface Logger {
    fun info(message: String)
    fun error(message: String, exception: Exception)
}