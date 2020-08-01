package com.javbus.server.config;

import com.javbus.server.dao.entity.Menu;
import org.apache.activemq.artemis.jms.client.ActiveMQQueue;
import org.springframework.context.annotation.Bean;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
import org.springframework.stereotype.Component;

import javax.jms.Destination;
import java.util.HashMap;
import java.util.Map;

@Component
public class ArtemisConfig {
    @Bean
    public Destination menuQueue() {
        return new ActiveMQQueue("menu.queue");
    }

//    @Bean
//    public MappingJackson2MessageConverter messageConverter() {
//        MappingJackson2MessageConverter messageConverter = new MappingJackson2MessageConverter();
//        messageConverter.setTypeIdPropertyName("_typeId");
//        Map<String, Class<?>> typeIdMappings = new HashMap<>();
//        typeIdMappings.put("menu", Menu.class);
//        messageConverter.setTypeIdMappings(typeIdMappings);
//        return messageConverter;
//    }
}
