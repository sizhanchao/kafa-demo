package com.zhan.kafka.consumer;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class KafkaConsumer {

    private final org.slf4j.Logger log = LoggerFactory.getLogger(getClass());
    @KafkaListener(topics = {"topic-ideal"})
    public void consumer(ConsumerRecord<?, ?> record) {
        Optional<?> kafkaMessage = Optional.ofNullable(record.value());
        if (kafkaMessage.isPresent()) {
            Object message = kafkaMessage.get();
            log .info("----------------- record =" + record);
            log.info("------------------ message =" + message);
        }
    }
}
