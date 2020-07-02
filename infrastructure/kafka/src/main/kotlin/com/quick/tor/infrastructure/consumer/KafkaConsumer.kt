package com.quick.tor.infrastructure.consumer

import com.quick.tor.infrastructure.OffsetBehaviour
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

fun consumer(
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