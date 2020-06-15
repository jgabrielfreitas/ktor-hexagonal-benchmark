package com.quick.tor.common

import java.util.UUID

fun String.toUUID() = UUID.fromString(this)
