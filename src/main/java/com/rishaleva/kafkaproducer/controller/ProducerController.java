package com.rishaleva.kafkaproducer.controller;

import com.rishaleva.kafkaproducer.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class ProducerController {

    private final KafkaTemplate<String, User> kafkaRestTemplate;

    private final String TOPIC = "user_topic";

    @PostMapping("/send")
    public String sendMessage(@RequestBody User user) {
        String key = UUID.randomUUID().toString();
        kafkaRestTemplate.send(TOPIC, key, user);
        return "Message sent successfully with key " + key + " and value " + user.toString();
    }
}
