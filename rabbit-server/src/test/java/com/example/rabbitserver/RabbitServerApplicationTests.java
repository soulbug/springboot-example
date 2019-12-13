package com.example.rabbitserver;

import com.example.rabbitserver.service.DirectExchangePublisher;
import com.example.rabbitserver.service.FanoutExchangePublisher;
import com.example.rabbitserver.service.TopicExchangePublisher;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class RabbitServerApplicationTests {
    @Autowired
    DirectExchangePublisher directExchangePublisher;
    @Autowired
    FanoutExchangePublisher fanoutExchangePublisher;
    @Autowired
    TopicExchangePublisher topicExchangePublisher;

    @Test
    void contextLoads() {
    }

    @Test
    void testDirectExchange() throws InterruptedException, JsonProcessingException {

        for (int i = 0; i < 100; i++) {
            String msg = i + "this is " + i + " direct message";
            ObjectMapper mapper = new ObjectMapper();
            String json = mapper.writeValueAsString(msg);
            MessageProperties messageProperties = new MessageProperties();
            messageProperties.setContentType(MessageProperties.CONTENT_TYPE_JSON);
            Message message = new Message(json.getBytes(), messageProperties);
            directExchangePublisher.sendMessage(message);
            Thread.sleep(100);
        }

    }

    @Test
    void testFanoutExchange() throws InterruptedException, JsonProcessingException {
        for (int i = 0; i < 100; i++) {
            String msg = i + "this is " + i + " direct message";
            ObjectMapper mapper = new ObjectMapper();
            String json = mapper.writeValueAsString(msg);
            MessageProperties messageProperties = new MessageProperties();
            messageProperties.setContentType(MessageProperties.CONTENT_TYPE_JSON);
            Message message = new Message(json.getBytes(), messageProperties);
            fanoutExchangePublisher.sendMessage(message);
            Thread.sleep(100);
        }
    }

    @Test
    void testTopicEmail() throws InterruptedException, JsonProcessingException {
        //String msg = "我想发送手机短信";
        // String msg = "我想发送邮箱短信";
        String msg = "我想发送所有";
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(msg);
        MessageProperties messageProperties = new MessageProperties();
        messageProperties.setContentType(MessageProperties.CONTENT_TYPE_JSON);
        Message message = new Message(json.getBytes(), messageProperties);
        //topicExchangePublisher.sendMessage(message, TopicConfig.ROUTING_KEY_PHONE);
        // topicExchangePublisher.sendMessage(message, TopicConfig.ROUTING_KEY_EMAIL);
        topicExchangePublisher.sendMessage(message, "message.phone");
        Thread.sleep(100);
    }
}
