package com.javbus.server.service.impl;

import com.javbus.server.dao.entity.Menu;
import com.javbus.server.service.JmsMenuMessageReceiverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.stereotype.Service;

import javax.jms.JMSException;
import javax.jms.Message;

@Service
public class JmsMenuMessageReceiverServiceImpl implements JmsMenuMessageReceiverService {

    @Autowired
    private JmsTemplate jmsTemplate;


    @Override
    public Menu receiverMenu() throws JMSException {
        Menu menu = (Menu) jmsTemplate.receiveAndConvert("menu.queue");
        return menu;
    }
}
