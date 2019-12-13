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
 * @date 2019/12/4 14:54
 * Version 1.0
 */
@Component
@Slf4j
public class FanoutExchangeListener {
    /**
     * 这儿使用了 SimpleRabbitListenerContainerFactory  @RabbitListener注解只能放在方法上
     * RabbitListener 绑定队列，当监听到队列中有消息时则会进行接收并处理
     * RabbitHandler 声明在方法上 搭配RabbitListener一起使用
     *
     * @param message 消息内容
     * @param channel 通道
     * @throws IOException IOException
     */
    @RabbitHandler
    @RabbitListener(queues = RabbitConfig.FANOUT_QUEUE_A)
    public void fanoutMessageA(Message message, Channel channel) throws IOException {
        long deliveryTag = message.getMessageProperties().getDeliveryTag();
        String msg = new String(message.getBody());
        log.info("队列：{} 接受到消息内容为：{}", RabbitConfig.FANOUT_QUEUE_A, msg);
        channel.basicAck(deliveryTag, false);
    }

    @RabbitHandler
    @RabbitListener(queues = RabbitConfig.FANOUT_QUEUE_B)
    public void fanoutMessageB(Message message, Channel channel) throws IOException {
        long deliveryTag = message.getMessageProperties().getDeliveryTag();
        String msg = new String(message.getBody());
        log.info("队列：{} 接受到消息内容为：{}", RabbitConfig.FANOUT_QUEUE_B, msg);
        channel.basicAck(deliveryTag, false);
    }

    @RabbitHandler
    @RabbitListener(queues = RabbitConfig.FANOUT_QUEUE_C)
    public void fanoutMessageC(Message message, Channel channel) throws IOException {
        long deliveryTag = message.getMessageProperties().getDeliveryTag();
        String msg = new String(message.getBody());
        log.info("队列：{} 接受到消息内容为：{}", RabbitConfig.FANOUT_QUEUE_C, msg);
        channel.basicAck(deliveryTag, false);
    }


}
