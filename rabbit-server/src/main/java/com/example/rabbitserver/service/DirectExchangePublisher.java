package com.example.rabbitserver.service;

import com.example.rabbitserver.config.rabbit.DirectConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

/**
 * @author ethan
 * @date 2019/12/12
 */
@Component
@Slf4j
public class DirectExchangePublisher extends AbstractCallback {
    public DirectExchangePublisher(RabbitTemplate rabbitTemplate) {
        super(rabbitTemplate);
    }

    public void sendMessage(Message message) {
        sendMessageToExchange(DirectConfig.DIRECT_EXCHANGE, DirectConfig.DIRECT_ROUTING_KEY, message);
    }
}
