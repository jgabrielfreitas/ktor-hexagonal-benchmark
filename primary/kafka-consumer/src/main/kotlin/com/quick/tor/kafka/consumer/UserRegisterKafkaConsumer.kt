package com.quick.tor.kafka.consumer

import com.quick.tor.infrastructure.consumer.clientConsumer
import com.quick.tor.kafka.UserMessageDto
import com.quick.tor.usecases.user.port.primary.UserPort
import com.sksamuel.avro4k.Avro

suspend fun consumerInsertUser(usePort: UserPort) {
    clientConsumer(
        topic = "user-command-request",
        group = "user-group",
        onReceiveRecord = { record ->

            val user = Avro.default.fromRecord(UserMessageDto.serializer(), record)
            usePort.save(user.toModel())
        }
    )
}
