package com.quick.tor.infrastructure

import kotlinx.serialization.Serializable
import org.apache.avro.Schema

@Serializable
abstract class BaseEvent {
    lateinit var id: String

    abstract fun toSchema(): Schema
}