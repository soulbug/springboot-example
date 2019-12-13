package com.example.springmonitor;

import com.example.springmonitor.publisher.DemoPublisher;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SpringMonitorApplicationTests {
    @Autowired
    DemoPublisher demoPublisher;

    @Test
    void contextLoads() {
        demoPublisher.publish(1,"this is message");
    }

}
