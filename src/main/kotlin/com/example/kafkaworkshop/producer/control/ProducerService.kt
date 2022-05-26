package com.example.kafkaworkshop.producer.control

import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Service

@Service
class ProducerService @Autowired internal constructor(
    private val kafkaTemplate: KafkaTemplate<String, String>
) {

    companion object {
        private val LOG = LoggerFactory.getLogger(ProducerService::class.java)
        private const val TOPIC = "topic"
        private const val DEFAULT_KEY = "key"
    }

    fun sendMessage(message: String) {
        kafkaTemplate.send(TOPIC, message::class.simpleName ?: DEFAULT_KEY, message)
        LOG.info("Message sent: $message")
    }
}