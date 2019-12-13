package com.example.rabbitserver.config.rabbit;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

import static com.example.rabbitserver.config.rabbit.DeadAndBackUpConfig.DIR_EXCHANGE;

/**
 * @author ethan direct 交换机
 * @date 2019/12/12
 */
@Configuration
public class DirectConfig {


    public static final String DIRECT_EXCHANGE = "direct_exchange";
    private static final String DIRECT_QUEUE = "direct_queue";
    public static final String DIRECT_ROUTING_KEY = "direct_routing_key";

    @Bean
    public Queue directQueue() {
        Map<String, Object> args = new HashMap<>(5);
        //配置死信交换器
        args.put("x-dead-letter-exchange", DIR_EXCHANGE);
        //声明队列消息过期时间 5000ms
        args.put("x-message-ttl", 5000);
        // 第一个参数是创建的queue的名字，第二个参数是是否支持持久化
        return new Queue(DIRECT_QUEUE, true, false, false, args);
    }

    @Bean
    public DirectExchange directExchange() {
        Map<String, Object> arguments = new HashMap<>(4);
        //配置备份交换器
        arguments.put("alternate-exchange", "exchange.backup");
        // 一共有三种构造方法，可以只传exchange的名字， 第二种，可以传exchange名字，是否支持持久化，是否可以自动删除，
        //第三种在第二种参数上可以增加Map，Map中可以存放自定义exchange中的参数
        return new DirectExchange(DIRECT_EXCHANGE, true, false, arguments);
    }

    @Bean
    public Binding directBinding() {
        // 把队列和exchange绑定在一起 路由键与队列名完全匹配。
        return BindingBuilder.bind(directQueue()).to(directExchange()).with(DIRECT_ROUTING_KEY);
    }


}