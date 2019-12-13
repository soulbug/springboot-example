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
 * Description Direct类型的监听器 本类模拟多个负载均衡
 *
 * @author ethan
 * @date 2019/12/3
 * Version 1.0
 */
@Component
@Slf4j
public class DirectExchangeListener {
    @RabbitHandler
    @RabbitListener(queues = RabbitConfig.DIRECT_QUEUE)
    public void directProcess(Message message, Channel channel) throws IOException {
        // 当一个消费者向 RabbitMQ 注册后，会建立起一个 Channel ，RabbitMQ 会用 basic.deliver 方法向消费者推送消息
        // ，这个方法携带了一个 delivery tag， 它代表了 RabbitMQ 向该 Channel 投递的这条消息的唯一标识 ID，是一个单调递增的正整数，每次接收消息+1
        // delivery tag 的范围仅限于 Channel， 不同的 channel 有独立的 deliveryTag。比如有两个消费者，你会发现，都是从 1 开始递增，互不影响。
        long deliveryTag = message.getMessageProperties().getDeliveryTag();
        String msg = new String(message.getBody());
        if (msg.contains("5")) {
            //第一个参数消息的唯一标识，第二个参数是否批量拒绝多条消息
            //channel.basicReject(deliveryTag,false);
            //第一个参数消息的唯一标识，第二个参数是否批量处理
            // 为了减少网络流量，手动确认可以被批处理，当该参数为 true 时，则可以一次性确认 delivery_tag 小于等于传入值的所有消息
            //第三个参数是否重回队列 basicNack是basicReject的加强版 basicReject不可以批量拒绝消息
            channel.basicNack(deliveryTag, false, false);
            log.info("ONE 处理失败拒绝消息:" + msg);
        } else {
            //手动处理消息 是否批量处理.true:一次性确认 delivery_tag 小于等于传入值的所有消息
            channel.basicAck(deliveryTag, false);
            log.info("ONE 处理成功:" + msg);
        }

    }

}
