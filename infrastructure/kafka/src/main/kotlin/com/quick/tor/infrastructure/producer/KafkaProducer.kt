package com.quick.tor.infrastructure.producer

import com.quick.tor.infrastructure.Acks
import com.quick.tor.infrastructure.BatchSize
import com.quick.tor.infrastructure.Compression
import io.confluent.kafka.serializers.KafkaAvroSerializer
import org.apache.avro.generic.GenericRecord
import org.apache.kafka.clients.producer.*
import org.apache.kafka.common.serialization.StringSerializer
import kotlin.collections.set
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

fun producer(
    bootstrapServers: String,
    idempotence: Boolean = true,
    acks: Acks = Acks.All,
    retries: Int = Int.MAX_VALUE,
    requestPerConnection: Int = 5,
    compression: Compression = Compression.Snappy,
    linger: Int = 20,
    batchSize: BatchSize = BatchSize.ThirtyTwo,
    schemaUrl: String
): KafkaProducer<String, GenericRecord> {
    return kafkaProducer(
        bootstrapServers,
        idempotence,
        acks,
        retries,
        requestPerConnection,
        compression,
        linger,
        batchSize,
        schemaUrl
    )
}

fun kafkaProducer(
    bootstrapServers: String,
    idempotence: Boolean,
    acks: Acks,
    retries: Int,
    requestPerConnection: Int,
    compression: Compression,
    linger: Int,
    batchSize: BatchSize,
    schemaUrl: String
): KafkaProducer<String, GenericRecord> {
    val prop: HashMap<String, Any> = HashMap()
    prop[ProducerConfig.BOOTSTRAP_SERVERS_CONFIG] = bootstrapServers
    prop[ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG] = StringSerializer::class.java.name
    prop[ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG] = KafkaAvroSerializer::class.java.name
    prop[ProducerConfig.ENABLE_IDEMPOTENCE_CONFIG] = idempotence.toString()
    prop[ProducerConfig.ACKS_CONFIG] = acks.value
    prop[ProducerConfig.RETRIES_CONFIG] = retries.toString()
    prop[ProducerConfig.MAX_IN_FLIGHT_REQUESTS_PER_CONNECTION] = requestPerConnection.toString()
    prop[ProducerConfig.COMPRESSION_TYPE_CONFIG] = compression.value
    prop[ProducerConfig.LINGER_MS_CONFIG] = linger.toString()
    prop[ProducerConfig.BATCH_SIZE_CONFIG] = batchSize.value
    prop[SCHEMA_REGISTRY_URL] = schemaUrl

    return KafkaProducer(prop)
}

private const val SCHEMA_REGISTRY_URL = "schema.registry.url"

suspend inline fun <reified K : Any, reified V : Any> KafkaProducer<K, V>.dispatch(record: ProducerRecord<K, V>) =
    suspendCoroutine<RecordMetadata> { continuation ->
        val callback = Callback { metadata, exception ->
            if (metadata == null) continuation.resumeWithException(exception!!)
            else continuation.resume(metadata)
        }
        this.send(record, callback).get()
    }
