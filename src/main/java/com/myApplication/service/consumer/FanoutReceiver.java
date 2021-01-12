package com.myApplication.service.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class FanoutReceiver {

    @RabbitHandler
    @RabbitListener(queues = "fanout.A")
    public void process1(Map testMessage) {
        System.out.println("FanoutReceiverA消费者收到消息  : " + testMessage.toString());
    }


    @RabbitHandler
    @RabbitListener(queues = "fanout.B")
    public void process2(Map testMessage) {
        System.out.println("FanoutReceiverB消费者收到消息  : " + testMessage.toString());
    }


    @RabbitHandler
    @RabbitListener(queues = "fanout.C")
    public void process3(Map testMessage) {
        System.out.println("FanoutReceiverC消费者收到消息  : " + testMessage.toString());
    }
}
