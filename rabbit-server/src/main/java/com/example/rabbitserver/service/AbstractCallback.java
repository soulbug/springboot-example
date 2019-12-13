package com.example.rabbitserver.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.UUID;

/**
 * Description 需要回调确认类
 *
 * @author ethan
 * @date 2019/12/3 14:16
 * Version 1.0
 */
@Slf4j
public abstract class AbstractCallback implements RabbitTemplate.ConfirmCallback, RabbitTemplate.ReturnCallback {
    private RabbitTemplate rabbitTemplate;

    @Autowired
    public AbstractCallback(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
        this.rabbitTemplate.setConfirmCallback(this);
        this.rabbitTemplate.setReturnCallback(this);
        log.info("初始化rabbitTemplate");
    }

    /**
     * 发送消息至指定的Exchange
     *
     * @param exchange        exchange名
     * @param routKey         routKey
     * @param message         消息
     * @param correlationData 唯一标识消息回调确认
     */
    public void sendMessageToExchange(String exchange, String routKey, Object message, CorrelationData correlationData) {
        log.info("发送exchange为：{}，routkey为：{}，消息内容：{}，标识信息：{}", exchange, routKey, message, correlationData);
        rabbitTemplate.convertAndSend(exchange, routKey, message, correlationData);
    }

    /**
     * 发送消息至指定的Exchange
     *
     * @param exchange exchange名
     * @param routKey  routKey
     * @param message  消息
     */
    public void sendMessageToExchange(String exchange, String routKey, Object message) {
        CorrelationData correlationData = new CorrelationData(UUID.randomUUID().toString());
        sendMessageToExchange(exchange, routKey, message, correlationData);
    }

    /**
     * 发送消息至指定的Exchange
     *
     * @param exchange exchange名
     * @param message  消息
     */
    public void sendMessageToExchange(String exchange, Object message) {
        sendMessageToExchange(exchange, "", message);
    }

    /**
     * //消息回调确认 异步
     *
     * @param correlationData 发送的uuid
     * @param ack             成功失败
     * @param cause           原因
     */
    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String cause) {
        if (ack) {
            log.info("消息发送成功:correlationData({}),ack({}),cause({})", correlationData, ack, cause);
        } else {
            log.info("消息发送失败:correlationData({}),ack({}),cause({})", correlationData, ack, cause);

        }
    }

    /**
     * Exchange路由到Queue失败才会回调这个方法。
     *
     * @param message
     * @param replyCode
     * @param replyText
     * @param exchange
     * @param routingKey
     */
    @Override
    public void returnedMessage(Message message, int replyCode, String replyText, String exchange, String routingKey) {
        log.info("消息丢失:exchange({}),route({}),replyCode({}),replyText({}),message:{}", exchange, routingKey, replyCode, replyText, message);
    }

}
