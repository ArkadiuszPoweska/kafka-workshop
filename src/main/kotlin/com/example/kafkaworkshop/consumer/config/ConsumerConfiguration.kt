package com.example.kafkaworkshop.consumer.config

import org.apache.kafka.clients.consumer.ConsumerConfig
import org.apache.kafka.common.serialization.StringDeserializer
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.annotation.EnableKafka
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory
import org.springframework.kafka.core.ConsumerFactory
import org.springframework.kafka.core.DefaultKafkaConsumerFactory

@EnableKafka
@Configuration
class ConsumerConfiguration(
    @Value("\${consumer.server.path}") private val serverPath: String
) {

    companion object {
        const val GROUP_ID = "example"
    }

    @Bean
    fun consumerFactory(): ConsumerFactory<String, String> {
        val properties: MutableMap<String, Any> = HashMap()
        properties[ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG] = serverPath
        properties[ConsumerConfig.GROUP_ID_CONFIG] = GROUP_ID
        properties[ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG] = StringDeserializer::class.java
        properties[ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG] = StringDeserializer::class.java
        return DefaultKafkaConsumerFactory(properties)
    }

    @Bean
    fun listenerContainerFactory(): ConcurrentKafkaListenerContainerFactory<String, String> {
        val factory = ConcurrentKafkaListenerContainerFactory<String, String>()
        factory.consumerFactory = consumerFactory()
        return factory
    }
}