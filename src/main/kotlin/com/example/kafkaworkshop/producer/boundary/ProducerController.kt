package com.example.kafkaworkshop.producer.boundary

import com.example.kafkaworkshop.producer.control.ProducerService
import org.springframework.http.HttpEntity
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/producer")
class ProducerController(
    private val producerService: ProducerService
) {

    @PostMapping
    fun produceMessage(@RequestBody message: String): HttpEntity<*> {
        producerService.sendMessage(message)
        return ResponseEntity.EMPTY
    }
}