package com.poc.rest;

import com.poc.message.Greeting;
import com.poc.producer.MessageProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/publish")
public class ProducerController {

    private final MessageProducer messageProducer;

    @Autowired
    public ProducerController(MessageProducer messageProducer) {
        this.messageProducer = messageProducer;
    }

    @PostMapping("/greeting")
    public void publishGreeting(@RequestBody Greeting body) {
        messageProducer.sendGreetingMessage(body);
    }

    @PostMapping("/greeting/{partition}")
    public void publishGreeting1(@RequestBody Greeting body, @PathVariable Integer partition) {
        messageProducer.sendMessageToPartition(body.toString(), partition);
    }


}
