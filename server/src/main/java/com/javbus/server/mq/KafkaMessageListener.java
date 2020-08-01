package com.javbus.server.mq;

import com.javbus.server.dao.entity.Menu;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

@Component
public class KafkaMessageListener {

//    @KafkaListener(topics = "menu.kafka.topic")
//    public void handle(Menu menu, Message<Menu> message) {
//        System.out.println("KafkaListener: " + menu);
//        message.getHeaders().getTimestamp();
//        Menu result = message.getPayload();  // 通过message对象的getPayload方法获取消息载荷
//    }
}
