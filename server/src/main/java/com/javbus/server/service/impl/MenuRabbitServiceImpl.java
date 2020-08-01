package com.javbus.server.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.javbus.server.dao.entity.Menu;
import com.javbus.server.service.MenuRabbitService;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;

@Service
public class MenuRabbitServiceImpl implements MenuRabbitService {

    @Autowired
    RabbitTemplate rabbitTemplate;

    @Override
    public void sendMenu(Menu menu) {
        rabbitTemplate.convertAndSend("menu.topic", "menu.routing", menu, new MessagePostProcessor() {
            @Override
            public Message postProcessMessage(Message message) throws AmqpException {
                MessageProperties pops = message.getMessageProperties();
                pops.setHeader("X_MENU_SOURCE", "web");
                return message;
            }
        });
    }

    @Override
    public Menu receive() {
        Menu menu = rabbitTemplate.receiveAndConvert("menu.rabbit", new ParameterizedTypeReference<Menu>() {
        });
        return menu;
    }

    @Override
    public Object receiveMenu() {
        Message message1 = rabbitTemplate.receive("menu.rabbit");
        byte[] message2 = message1.getBody();
        MessageProperties message = message1.getMessageProperties();
        Menu menu = JSONObject.parseObject(message2, Menu.class);
        System.out.println(message1);
        System.out.println(menu);
        System.out.println(message.getPriority());
        System.out.println(message.getHeaders());
        return menu;
    }
}
