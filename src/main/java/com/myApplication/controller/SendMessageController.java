package com.myApplication.controller;

import com.alibaba.fastjson.JSON;
import com.myApplication.entity.Book;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/*SpringBoot整合Rabbitmq*/
@Controller
public class SendMessageController {
    @Autowired
    RabbitTemplate rabbitTemplate;  //使用RabbitTemplate,这提供了接收/发送等等方法

    @Autowired
    MongoTemplate mongoTemplate;

    @GetMapping("/sendDirectMessage")
    @ResponseBody
    public String sendDirectMessage() {
        String messageId = String.valueOf(UUID.randomUUID());
        String messageData = "test message, hello!";
        String createTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        Map<String, Object> map = new HashMap<>();
        map.put("messageId", messageId);
        map.put("messageData", messageData);
        map.put("createTime", createTime);
        //将消息携带绑定键值：TestDirectRouting 发送到交换机TestDirectExchange
        rabbitTemplate.convertAndSend("TestDirectExchange", "TestDirectRouting", map);
        return "ok";
    }

    @GetMapping("/sendJson")
    @ResponseBody
    public String sendJson(String id) {
        Book myBook = mongoTemplate.findOne(Query.query(Criteria.where("_id").is(id)), Book.class);
        String json = JSON.toJSONString(myBook);  // 将对象转化为json
        rabbitTemplate.convertAndSend("TestDirectExchange", "TestDirectRouting", json);
        return json;
    }

    @GetMapping("/sendJava")
    @ResponseBody
    public String sendJava(String id) {
        Book myBook = mongoTemplate.findOne(Query.query(Criteria.where("_id").is(id)), Book.class);
        rabbitTemplate.convertAndSend("TestDirectExchange", "TestDirectRouting", myBook);
        return "ok";
    }
}
