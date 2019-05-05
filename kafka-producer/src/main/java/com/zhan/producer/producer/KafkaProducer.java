package com.zhan.producer.producer;

import com.alibaba.fastjson.JSON;
import com.zhan.producer.bean.Message;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.UUID;


@Component
public class KafkaProducer {
    private final org.slf4j.Logger log = LoggerFactory.getLogger(getClass());
    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    //发送消息方法
    @Scheduled(cron = "*/2 * * * * ?")
//    @Scheduled(fixedRate = 5000)
    public void send() {
        Message message = new Message();
        message.setId(System.currentTimeMillis());
        message.setMsg(UUID.randomUUID().toString());
        message.setSendTime(new Date());
        log.info("+++++++++++++++++++++  message = {}", JSON.toJSONString(message));
        //topic-ideal为主题
        kafkaTemplate.send("topic-ideal",JSON.toJSONString(message));
    }
}
