package com.javbus.server.mq;

import com.javbus.server.dao.entity.Menu;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class RabbitMQListener {

//    @RabbitListener(queues = "menu.rabbit")
//    public void receiveMenu(Menu menu) {
//        System.out.println(menu);
//    }
}
