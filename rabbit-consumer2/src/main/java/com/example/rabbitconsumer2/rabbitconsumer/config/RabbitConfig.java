package com.example.rabbitconsumer2.rabbitconsumer.config;

import org.springframework.amqp.core.AcknowledgeMode;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author ethan
 * @date 2019/12/12
 */
@Configuration
public class RabbitConfig {
    public static final String DIRECT_QUEUE = "direct_queue";
    public static final String FANOUT_QUEUE_A = "fanout_queue_a";
    public static final String FANOUT_QUEUE_B = "fanout_queue_b";
    public static final String FANOUT_QUEUE_C = "fanout_queue_c";

    /**
     * 1.使用 jdk 序列化将消息转成字节数组，转出来的结果较大，含class类名，类相应方法等信息，因此性能较差。
     * 2.生产者发送String消息时解析失败。
     * Jackson2JsonMessageConverter 序列化形式以此提高性能和成功解析
     * 代码中重新设置SimpleRabbitListenerContainerFactory则配置文件不生效，需再次设置
     *
     * @param connectionFactory
     * @return
     */
    @Bean
    public SimpleRabbitListenerContainerFactory rabbitListenerContainerFactory(ConnectionFactory connectionFactory) {
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);
        factory.setMessageConverter(new Jackson2JsonMessageConverter());
        factory.setAcknowledgeMode(AcknowledgeMode.MANUAL);
        factory.setConcurrentConsumers(1);
        factory.setMaxConcurrentConsumers(10);
        factory.setPrefetchCount(2);
        return factory;
    }
}