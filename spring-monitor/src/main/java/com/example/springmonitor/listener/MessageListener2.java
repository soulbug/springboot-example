package com.example.springmonitor.listener;

import com.example.springmonitor.event.MessageEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/**
 * Description TODO
 *
 * @author ethan
 * @date 2019/12/5 18:25
 * Version 1.0
 */
@Component
public class MessageListener2 {
    @EventListener
    public void onApplicationEvent(MessageEvent event) {
        System.out.println(">>>>>>>>>DemoListener2>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        System.out.println("收到了：" + event.getSource() + "消息;时间：" + event.getTimestamp());
        System.out.println("消息：" + event.getId() + ":" + event.getMessage());
    }
}
