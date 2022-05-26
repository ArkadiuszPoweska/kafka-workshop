package com.example.kafkaworkshop.consumer.control

import com.example.kafkaworkshop.consumer.config.ConsumerConfiguration
import org.apache.kafka.clients.consumer.ConsumerRecord
import org.slf4j.LoggerFactory
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Service

@Service
class ConsumerService {

    companion object {
        private val LOG = LoggerFactory.getLogger(ConsumerService::class.java)
        private const val TOPIC = "topic"
        private const val CONTAINER_FACTORY = "listenerContainerFactory"
    }

    @KafkaListener(topics = [TOPIC], groupId = ConsumerConfiguration.GROUP_ID, containerFactory = CONTAINER_FACTORY)
    private fun receiveMessage(message: ConsumerRecord<String, String>) {
        LOG.info("Message received: ${message.value()}")
    }
}