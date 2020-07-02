package com.quick.tor.kafka.consumer

import com.quick.tor.infrastructure.OffsetBehaviour
import com.quick.tor.infrastructure.OffsetBehaviour.Earliest
import com.quick.tor.infrastructure.consumer.consumer
import com.quick.tor.kafka.UserMessageDTO
import com.quick.tor.usecases.user.port.primary.UserPort
import com.sksamuel.avro4k.Avro
import org.apache.avro.generic.GenericRecord
import java.time.Duration

suspend fun consumerInsertUser(usePort: UserPort) {
    consumerCommand(
        topic = "user-command-request",
        bootstrapServers = "localhost:9092",
        group = "user-group",
        autoCommit = false,
        offsetBehaviour = Earliest,
        pollMax = 10,
        pollDuration = 1000,
        usePort = usePort,
        schemaUrl = "http://localhost:8081"
    )
}

suspend fun consumerCommand(
    topic: String,
    bootstrapServers: String,
    group: String,
    autoCommit: Boolean,
    offsetBehaviour: OffsetBehaviour,
    pollMax: Int,
    pollDuration: Long,
    usePort: UserPort,
    schemaUrl: String
) {
    val consumer = consumer(bootstrapServers, group, autoCommit, offsetBehaviour, pollMax, schemaUrl)
    consumer.subscribe(mutableListOf(topic))
    while(true) {
        val records = consumer.poll(Duration.ofMillis(pollDuration))
        if (records.count() > 0) {
            records.forEach {
                val record = it.value() as GenericRecord
                val entity = Avro.default.fromRecord(UserMessageDTO.serializer(), record)
//                val user = UserMessageDTO(record)
//                usePort.save(user.toModel())
            }
        }
    }
}
