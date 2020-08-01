package com.javbus.server.mq;

import com.javbus.server.dao.entity.Menu;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

/**
 * Armetis监听组件
 */
@Slf4j
@Component
public class MenuListener {

//    @SendTo // receiver获取消息需要有返回值是需要加入该注解，不加注解且方法需要返回值时会报异常
//    @JmsListener(destination = "menu.queue")
//    public void receiveMenu(Menu menu) {
//      log.info("监听Artemis获取到的菜单信息: {}", menu);
//    }
}
