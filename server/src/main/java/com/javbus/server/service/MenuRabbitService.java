package com.javbus.server.service;

import com.javbus.server.dao.entity.Menu;

public interface MenuRabbitService {
    public void sendMenu(Menu menu);

    public Menu receive();

    public Object receiveMenu();
}
