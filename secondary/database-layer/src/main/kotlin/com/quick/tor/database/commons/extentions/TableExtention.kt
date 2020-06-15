package com.quick.tor.database.commons.extentions

import org.jetbrains.exposed.dao.id.IdTable
import java.util.UUID

open class StringIdTable(name: String = "", columnName: String = "id") : IdTable<String>(name) {
    override val id = uuid()
    override val primaryKey by lazy { super.primaryKey ?: PrimaryKey(id) }
}

fun IdTable<String>.uuid() = text("id").clientDefault { UUID.randomUUID().toString() }.entityId()