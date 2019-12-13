package com.example.springmonitor.listener;

import com.example.springmonitor.event.MessageEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * Description 基于实现ApplicationListener 接口
 *
 * @author ethan
 * @date 2019/12/5 18:22
 * Version 1.0
 */
@Component
public class MessageListener implements ApplicationListener<MessageEvent> {


    @Override
    public void onApplicationEvent(MessageEvent event) {
        System.out.println(">>>>>>>>>DemoListener>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        System.out.println("收到了：" + event.getSource() + "消息;时间：" + event.getTimestamp());
        System.out.println("消息：" + event.getId() + ":" + event.getMessage());
    }
}
