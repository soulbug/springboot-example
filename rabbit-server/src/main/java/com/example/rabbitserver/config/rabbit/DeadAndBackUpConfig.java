package com.example.rabbitserver.config.rabbit;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Description 设置消息不丢失
 *
 * @author ethan
 * @date 2019/12/5 11:26
 * Version 1.0
 */
@Configuration
public class DeadAndBackUpConfig {

    public static final String DIR_EXCHANGE = "exchange.dlx";
    public static final String BACKUP_EXCHANGE = "exchange.backup";
    private static final String DLX_QUEUE = "queue.dlx";
    private static final String BACKUP_QUEUE = "queue.backup";


    /**
     * 没有路由到的消息将进入此队列 returnedMessage和confirm
     *
     * @return
     */
    @Bean
    public Queue backupQueue() {
        return new Queue(BACKUP_QUEUE);
    }

    /**
     * 死信队列
     *
     * @return
     */
    @Bean
    public Queue dlxQueue() {
        return new Queue(DLX_QUEUE);
    }


    @Bean
    public FanoutExchange backupExchange() {
        // 此处的交换器的名字要和 exchange() 方法中 alternate-exchange 参数的值一致
        return new FanoutExchange(BACKUP_EXCHANGE);
    }

    /**
     * 申明死信交换器
     *
     * @return
     */
    @Bean
    public FanoutExchange dlxExchange() {
        return new FanoutExchange(DIR_EXCHANGE);
    }


    @Bean
    public Binding backupBinding() {
        return BindingBuilder.bind(backupQueue()).to(backupExchange());
    }

    @Bean
    public Binding dlxBinding() {
        return BindingBuilder.bind(dlxQueue()).to(dlxExchange());
    }

}
