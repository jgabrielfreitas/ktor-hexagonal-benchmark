package com.quick.tor.infrastructure.consumer

import com.quick.tor.infrastructure.OffsetBehaviour
import com.quick.tor.infrastructure.OffsetBehaviour.Latest
import io.confluent.kafka.serializers.KafkaAvroDeserializer
import org.apache.avro.generic.GenericRecord
import org.apache.kafka.clients.CommonClientConfigs.BOOTSTRAP_SERVERS_CONFIG
import org.apache.kafka.clients.consumer.ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG
import org.apache.kafka.clients.consumer.ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG
import org.apache.kafka.clients.consumer.ConsumerConfig.GROUP_ID_CONFIG
import org.apache.kafka.clients.consumer.ConsumerConfig.AUTO_OFFSET_RESET_CONFIG
import org.apache.kafka.clients.consumer.ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG
import org.apache.kafka.clients.consumer.ConsumerConfig.MAX_POLL_RECORDS_CONFIG
import org.apache.kafka.clients.consumer.KafkaConsumer
import org.apache.kafka.common.serialization.StringDeserializer
import java.time.Duration

suspend fun clientConsumer(
    topic: String,
    bootstrapServers: String = "localhost:9092",
    group: String,
    autoCommit: Boolean = false,
    offsetBehaviour: OffsetBehaviour = Latest,
    pollMax: Int = 10,
    pollDuration: Long = 1000,
    onReceiveRecord: suspend (record: GenericRecord) -> Unit,
    schemaUrl: String = "http://localhost:8081"
) {
    val consumer = kafkaConsumer(bootstrapServers, group, autoCommit, offsetBehaviour, pollMax, schemaUrl)
    consumer.subscribe(mutableListOf(topic))
    while(true) {
        val records = consumer.poll(Duration.ofMillis(pollDuration))
        if (records.count() > 0) {
            records.forEach {
                onReceiveRecord(it.value())
            }
        }
    }
}

fun kafkaConsumer(
    bootstrapServers: String,
    group: String,
    autoCommit: Boolean?,
    offsetBehaviour: OffsetBehaviour,
    pollMax: Int,
    schemaUrl: String
): KafkaConsumer<String, GenericRecord> {
    val prop: HashMap<String, Any> = HashMap()
    prop[BOOTSTRAP_SERVERS_CONFIG] = bootstrapServers
    prop[KEY_DESERIALIZER_CLASS_CONFIG] = StringDeserializer::class.java.name
    prop[VALUE_DESERIALIZER_CLASS_CONFIG] = KafkaAvroDeserializer::class.java.name
    prop[GROUP_ID_CONFIG] = group
    prop[AUTO_OFFSET_RESET_CONFIG] = offsetBehaviour.value
    prop[ENABLE_AUTO_COMMIT_CONFIG] = autoCommit.toString()
    prop[MAX_POLL_RECORDS_CONFIG] = pollMax.toString()
    prop[SCHEMA_REGISTRY_URL] = schemaUrl

    return KafkaConsumer(prop)
}
private const val SCHEMA_REGISTRY_URL = "schema.registry.url"