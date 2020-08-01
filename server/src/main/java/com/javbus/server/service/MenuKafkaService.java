package com.javbus.server.service;

import com.javbus.server.dao.entity.Menu;

public interface MenuKafkaService {
    public void sendMenu (Menu menu);
}
