package com.example.rabbitserver.service;

import com.example.rabbitserver.config.rabbit.FanoutConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

/**
 * Description TODO
 *
 * @author ethan
 * @date 2019/12/4 14:25
 * Version 1.0
 */
@Component
@Slf4j
public class FanoutExchangePublisher extends AbstractCallback {

    public FanoutExchangePublisher(RabbitTemplate rabbitTemplate) {
        super(rabbitTemplate);
    }

    public void sendMessage(Message message) {
        sendMessageToExchange(FanoutConfig.FANOUT_EXCHANGE, message);
    }
}
