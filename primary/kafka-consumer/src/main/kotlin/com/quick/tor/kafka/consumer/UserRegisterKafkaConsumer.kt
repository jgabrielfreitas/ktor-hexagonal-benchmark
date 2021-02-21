package com.quick.tor.kafka.consumer

import com.github.avrokotlin.avro4k.Avro
import com.quick.tor.infrastructure.consumer.clientConsumer
import com.quick.tor.kafka.UserMessageDto
import com.quick.tor.log.Logger
import com.quick.tor.usecases.user.port.primary.UserPort

suspend fun consumerInsertUser(
    bootstrapServers: String,
    schemaUrl: String,
    usePort: UserPort,
    log: Logger
) {
    clientConsumer(
        topic = "user-event",
        group = "ktor-user-group",
        bootstrapServers = bootstrapServers,
        schemaUrl = schemaUrl,

        onReceiveRecord = { record ->

            log.info("new record: $record")

            val user = Avro.default.fromRecord(UserMessageDto.serializer(), record)
            usePort.save(user.toModel())

            log.info("user saved successfully with record $record")
        },

        onError = { record, exception ->
            // example: send record to dlq
            log.error("sending record to DLQ: $record", exception)
        }
    )
}
