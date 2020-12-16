package com.myApplication.service.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class TopicReceiver {

    @RabbitHandler
    @RabbitListener(queues = "topic.man")
    public void process1(Map testMessage) {
        System.out.println("TopicManReceiver消费者收到消息  : " + testMessage.toString());
    }

    @RabbitHandler
    @RabbitListener(queues = "topic.woman")
    public void process2(Map testMessage) {
        System.out.println("TopicTotalReceiver消费者收到消息  : " + testMessage.toString());
    }

}
