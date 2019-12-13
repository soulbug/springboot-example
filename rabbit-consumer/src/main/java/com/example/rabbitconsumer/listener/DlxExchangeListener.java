package com.example.rabbitconsumer.listener;

import com.example.rabbitconsumer.config.RabbitConfig;
import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

/**
 * Description TODO
 *
 * @author ethan
 * @date 2019/12/5 13:49
 * Version 1.0
 */
@Configuration
@Slf4j
public class DlxExchangeListener {

    @RabbitHandler
    @RabbitListener(queues = "queue.dlx")
    public void dlxProcess(Message message, Channel channel) throws IOException {
        String msg = new String(message.getBody());
        long deliveryTag = message.getMessageProperties().getDeliveryTag();
        channel.basicAck(deliveryTag, false);
        log.info("死信队列接受消息:" + msg);
    }

    @RabbitHandler
    @RabbitListener(queues = "queue.backup")
    public void backupProcess(Message message, Channel channel) throws IOException {
        String msg = new String(message.getBody());
        long deliveryTag = message.getMessageProperties().getDeliveryTag();
        channel.basicAck(deliveryTag, false);
        log.info("备份队列接受消息:" + msg);
    }
}
