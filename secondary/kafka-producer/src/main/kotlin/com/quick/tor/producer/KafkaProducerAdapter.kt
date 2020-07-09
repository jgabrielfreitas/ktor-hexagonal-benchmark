package com.quick.tor.producer

import com.quick.tor.infrastructure.producer.dispatch
import org.apache.avro.generic.GenericRecord
import org.apache.kafka.clients.producer.KafkaProducer
import org.apache.kafka.clients.producer.ProducerRecord

class KafkaProducerAdapter(
    val producer: KafkaProducer<String, GenericRecord>
) {

    suspend fun send(topicName: String, record: ProducerRecord<String, GenericRecord>) {

        producer.dispatch(record)
    }
}