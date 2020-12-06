package com.javbus.server.utils;

import com.javbus.server.dao.entity.Menu;
import com.javbus.server.service.MenuService;
import com.javbus.server.service.impl.MenuServiceImpl;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 *静态方法注入Bean示例
 */
@Component
public class StaticWriedUtil implements ApplicationContextAware {

    private static MenuService menuService;

    private ApplicationContext context;

    public static List<Menu> getMenuTree(){
        return menuService.getMenuTree();
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.context = applicationContext;
        StaticWriedUtil.menuService = context.getBean(MenuServiceImpl.class);

    }
}
