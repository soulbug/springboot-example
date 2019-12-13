package com.example.rabbitserver.service;

import com.example.rabbitserver.config.rabbit.DirectConfig;
import com.example.rabbitserver.config.rabbit.TopicConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

/**
 * Description TODO
 *
 * @author ethan
 * @date 2019/12/5 10:51
 * Version 1.0
 */
@Service
@Slf4j
public class TopicExchangePublisher extends AbstractCallback {
    public TopicExchangePublisher(RabbitTemplate rabbitTemplate) {
        super(rabbitTemplate);
    }

    public void sendMessage(Message message, String routKey) {
        sendMessageToExchange(TopicConfig.EXCHANGE_TOPIC_MESSAGE, routKey, message);
    }
}
