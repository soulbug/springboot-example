package com.example.rabbitconsumer.listener;

import com.example.rabbitconsumer.config.RabbitConfig;
import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * Description TODO
 *
 * @author ethan
 * @date 2019/12/5 10:55
 * Version 1.0
 */
@Component
@Slf4j
public class TopicExchangeListener {
    @RabbitHandler
    @RabbitListener(queues = RabbitConfig.QUEUE_EMAIL)
    public void fanoutMessageA(Message message, Channel channel) throws IOException {
        long deliveryTag = message.getMessageProperties().getDeliveryTag();
        String msg = new String(message.getBody());
        log.info("队列A：{} 接受到消息内容为：{}", RabbitConfig.QUEUE_EMAIL, msg);
        channel.basicAck(deliveryTag, false);
    }

    @RabbitHandler
    @RabbitListener(queues = RabbitConfig.QUEUE_PHONE)
    public void fanoutMessageB(Message message, Channel channel) throws IOException {
        long deliveryTag = message.getMessageProperties().getDeliveryTag();
        String msg = new String(message.getBody());
        log.info("队列：{} 接受到消息内容为：{}", RabbitConfig.QUEUE_PHONE, msg);
        channel.basicAck(deliveryTag, false);
    }

    @RabbitHandler
    @RabbitListener(queues = RabbitConfig.QUEUE_ALL)
    public void fanoutMessageC(Message message, Channel channel) throws IOException {
        long deliveryTag = message.getMessageProperties().getDeliveryTag();
        String msg = new String(message.getBody());
        log.info("队列：{} 接受到消息内容为：{}", RabbitConfig.QUEUE_ALL, msg);
        channel.basicAck(deliveryTag, false);
    }

    @RabbitHandler
    @RabbitListener(queues = RabbitConfig.FANOUT_QUEUE_A)
    public void fanoutMessageAA(Message message, Channel channel) throws IOException {
        long deliveryTag = message.getMessageProperties().getDeliveryTag();
        String msg = new String(message.getBody());
        log.info("队列AA：{} 接受到消息内容为：{}", RabbitConfig.FANOUT_QUEUE_A, msg);
        channel.basicAck(deliveryTag, false);
    }
}
