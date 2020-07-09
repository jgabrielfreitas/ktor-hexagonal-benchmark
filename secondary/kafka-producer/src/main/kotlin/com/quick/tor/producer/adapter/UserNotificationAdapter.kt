package com.quick.tor.producer.adapter

import com.quick.tor.log.Logger
import com.quick.tor.producer.KafkaProducerAdapter
import com.quick.tor.producer.dto.UserMessageDTO
import com.quick.tor.producer.dto.toMessageDTO
import com.quick.tor.usecases.user.model.UserEvent
import com.quick.tor.usecases.user.port.secondary.UserNotificationPort
import com.sksamuel.avro4k.Avro
import org.apache.avro.generic.GenericRecord
import org.apache.kafka.clients.producer.ProducerRecord

class UserNotificationAdapter(
    val producer: KafkaProducerAdapter,
    val logger: Logger
): UserNotificationPort {

    val topicNameToNofity: String = "user-event-created"

    override suspend fun notify(userEvent: UserEvent) {

        logger.info("notifying event: $userEvent")
        val eventDto = userEvent.toMessageDTO()

        val avroSchema = Avro.default.toRecord(UserMessageDTO.serializer(), eventDto)
        val record = ProducerRecord<String, GenericRecord>(topicNameToNofity, eventDto.id, avroSchema)
        producer.send(topicName = topicNameToNofity, record = record)

        logger.info("event published successfully")
    }
}