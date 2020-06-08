package com.quick.tor.common.exceptions

class RestMissingRequestParameterException(paramName: String, override val message: String = "missing request parameter: $paramName"): RuntimeException(message)