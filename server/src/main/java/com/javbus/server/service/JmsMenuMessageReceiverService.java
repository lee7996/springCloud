package com.javbus.server.service;

import com.javbus.server.dao.entity.Menu;

import javax.jms.JMSException;

public interface JmsMenuMessageReceiverService {
    public Menu receiverMenu() throws JMSException;
}
