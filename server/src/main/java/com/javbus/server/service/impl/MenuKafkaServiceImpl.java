package com.javbus.server.service.impl;

import com.javbus.server.dao.entity.Menu;
import com.javbus.server.service.MenuKafkaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Kafka 没有提供拉取消息的方法，需要消费来自Kafka的消息只能通过消息监听器
 */
@Service
public class MenuKafkaServiceImpl implements MenuKafkaService {

    @Autowired
    private KafkaTemplate<String, Menu> kafkaTemplate;

    @Override
    public void sendMenu(Menu menu) {
//        kafkaTemplate.send("menu.kafka.topic", menu); // 指定Topic发送消息
        kafkaTemplate.sendDefault(menu);  // 向默认的Topic发送消息
    }
}
