package com.example.rabbitserver.config.rabbit;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Description fanout 交换器
 *
 * @author ethan
 * @date 2019/12/4 14:15
 * Version 1.0
 */
@Configuration
public class FanoutConfig {
    public static final String FANOUT_EXCHANGE = "fanout_exchange";
    private static final String FANOUT_QUEUE_A = "fanout_queue_a";
    private static final String FANOUT_QUEUE_B = "fanout_queue_b";
    private static final String FANOUT_QUEUE_C = "fanout_queue_c";

    @Bean
    public FanoutExchange fanoutExchange() {
        return new FanoutExchange(FANOUT_EXCHANGE, true, false);
    }

    @Bean
    public Queue queueA() {
        return new Queue(FANOUT_QUEUE_A);
    }

    @Bean
    public Queue queueB() {
        return new Queue(FANOUT_QUEUE_B);
    }

    @Bean
    public Queue queueC() {
        return new Queue(FANOUT_QUEUE_C);
    }

    @Bean
    public Binding bindingQueueA() {
        return BindingBuilder.bind(queueA()).to(fanoutExchange());
    }

    @Bean
    public Binding bindingQueueB() {
        return BindingBuilder.bind(queueB()).to(fanoutExchange());
    }

    @Bean
    public Binding bindingQueueC() {
        return BindingBuilder.bind(queueC()).to(fanoutExchange());
    }
}
