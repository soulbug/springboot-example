package com.example.springmonitor.event;

import lombok.Data;
import org.springframework.context.ApplicationEvent;

/**
 * Description 自定义事件
 *
 * @author ethan
 * @date 2019/12/5 18:20
 * Version 1.0
 */
@Data
public class MessageEvent extends ApplicationEvent {
    /**
     * Create a new {@code ApplicationEvent}.
     *
     * @param source the object on which the event initially occurred or with
     * which the event is associated (never {@code null})
     */


    private Long id;
    private String message;

    public MessageEvent(Object source, Long id, String message) {
        super(source);
        this.id = id;
        this.message = message;
    }
}
