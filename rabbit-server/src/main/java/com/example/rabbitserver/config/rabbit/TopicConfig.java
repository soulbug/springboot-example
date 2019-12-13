package com.example.rabbitserver.config.rabbit;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Description TODO
 *
 * @author ethan
 * @date 2019/12/5 10:37
 * Version 1.0
 */
@Configuration
public class TopicConfig {
    public static final String EXCHANGE_TOPIC_MESSAGE = "exchange.topic.message";
    public static final String ROUTING_KEY_EMAIL = "*.email";
    public static final String ROUTING_KEY_PHONE = "*.phone";
    public static final String ROUTING_KEY_ALL = "message.*";
    public static final String QUEUE_EMAIL = "queue_email";
    public static final String QUEUE_PHONE = "queue_phone";
    public static final String QUEUE_ALL = "queue_all";

    @Bean
    public Queue queueEmail() {  //邮件队列
        return new Queue(QUEUE_EMAIL);
    }

    @Bean
    public Queue queuePhone() {  //手机短信队列
        return new Queue(QUEUE_PHONE);
    }

    @Bean
    public Queue queueAll() {
        return new Queue(QUEUE_ALL);
    }

    @Bean
    public Exchange topicExchange() {
        return ExchangeBuilder.topicExchange(EXCHANGE_TOPIC_MESSAGE).durable(true).build();
    }

    @Bean
    public Binding emailBindingTopic() {
        return BindingBuilder.bind(queueEmail()).to(topicExchange()).with(ROUTING_KEY_EMAIL).noargs();
    }

    @Bean
    public Binding phoneBindingTopic() {
        return BindingBuilder.bind(queuePhone()).to(topicExchange()).with(ROUTING_KEY_PHONE).noargs();
    }

    @Bean
    public Binding allBindingTopic() {
        return BindingBuilder.bind(queueAll()).to(topicExchange()).with(ROUTING_KEY_ALL).noargs();
    }
}
