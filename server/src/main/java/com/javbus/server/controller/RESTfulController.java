package com.javbus.server.controller;

import com.javbus.server.dao.entity.Menu;
import com.javbus.server.service.JmsMenuMessageReceiverService;
import com.javbus.server.service.JmsMenuMessageSendService;
import com.javbus.server.service.MenuRabbitService;
import com.javbus.server.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.jms.JMSException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
// produces:指定相应参数的类型(此处与responseBody效果相同);  consumes:指定该控制器的接口只处理heard的Content-Type的类型
@RequestMapping(path = "/base", produces = "application/json", consumes = "application/json")
@CrossOrigin(origins = "*") // 允许跨域
public class RESTfulController {

    @Autowired
    private MenuService menuService;

    @Autowired
    private JmsMenuMessageSendService menuMessageService;

    @Autowired
    private MenuRabbitService menuRabbitService;

    @Autowired
    private JmsMenuMessageReceiverService jmsMenuMessageReceiverService;
    @GetMapping
    @ResponseStatus(HttpStatus.CREATED) // 请求成功时的响应码
    public Resources menuList() {
        List<Menu> result = menuService.getMenuTree();
        Resources<Resource<Menu>> response = Resources.wrap(result);
        // 为资源添加超媒体连接
        response.add(ControllerLinkBuilder.linkTo(methodOn(RESTfulController.class).menuList()).withRel("menus"));
        return response;
    }

    @GetMapping("/{id}")
    public Object menuById(@PathVariable("id") String id) throws ParseException {
        Menu menu = menuService.menuById(id);
//        menuMessageService.convertAndSend(menu);  // Artemis 发送消息
        menuRabbitService.sendMenu(menu);
        return menu;
    }

    /**
     * 从MQ拉取菜单信息
     * @return
     */
    @GetMapping("/getMenu")
    public Object getMenu() {
//            return jmsMenuMessageReceiverService.receiverMenu();  // Artemis 拉取消息
            return menuRabbitService.receiveMenu();  // RabbitMQ 拉取消息

    }
}
