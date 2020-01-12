package com.javbus.server.service;

import com.javbus.server.dao.entity.Menu;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author yuan
 * @since 2019-10-18
 */
public interface MenuService extends IService<Menu> {

	Object getMenuTree();

}
