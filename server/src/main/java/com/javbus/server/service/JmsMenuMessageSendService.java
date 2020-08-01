package com.javbus.server.service;

import com.javbus.server.dao.entity.Menu;
import org.springframework.data.domain.Sort;

public interface JmsMenuMessageSendService {
    public void sendOrder(Menu menu);

    public void convertAndSend(Menu menu);
}

