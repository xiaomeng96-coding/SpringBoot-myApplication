package com.myApplication.service.consumer;

import com.myApplication.entity.Book;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RabbitListener(queues = "test_queue")     //监听的队列名称 TestDirectQueue
public class DirectReceiver {

    @RabbitHandler
    public void process(Map testMessage) {
        System.out.println("====== Map接收 ======");
        System.out.println("DirectReceiver消费者收到消息  : " + testMessage.toString());
    }

    @RabbitHandler
    public void process(String json) {
        System.out.println("====== Json接收 ======");
        System.out.println("DirectReceiver消费者收到消息  : " + json);
    }

    @RabbitHandler
    public void process(Book book) {
        System.out.println("====== Java对象接收 ======");
        System.out.println("DirectReceiver消费者收到消息  : " + book);
    }
}
