package com.javbus.server.service.impl;

import com.javbus.server.dao.entity.Menu;
import com.javbus.server.service.JmsMenuMessageSendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;

@Service
public class JmsMenuMessageSendServiceImpl implements JmsMenuMessageSendService {
    @Autowired
    private JmsTemplate jmsTemplate;
    @Resource
    Destination menuQueue;

    @Override
    public void sendOrder(Menu menu) {
//        jmsTemplate.send(new MessageCreator() {
//            @Override
//            public Message createMessage(Session session) throws JMSException {
//                return session.createObjectMessage(menu);
//            }
//        });
        // 通过Destination指定目的地的时候，我们还可以设置Destination的更多属性，而不仅仅是目的地名称。
        // 但是在实践中我们除了目的地名称外几乎不会设置其他的属性。因此，使用名称作为send()的第一个参数会更简便。
//        jmsTemplate.send(menuQueue, session -> session.createObjectMessage(menu));
        // 通过lambda表达式实现
        jmsTemplate.send("menu.queue", session -> session.createObjectMessage(menu));
    }

    @Override
    public void convertAndSend(Menu menu) {
        // convertAndSend()内部自动将menu转化为message
        jmsTemplate.convertAndSend("menu.queue", menu, this :: addMenuSource);
    }

    private Message addMenuSource(Message message) throws JMSException {
        message.setStringProperty("menu_source", "WEB");
        return message;
    }
}
