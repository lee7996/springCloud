package com.javbus.server.service.impl;

import com.javbus.server.dao.entity.Menu;
import com.javbus.server.mapper.MenuMapper;
import com.javbus.server.service.MenuService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author yuan
 * @since 2019-10-18
 */
@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements MenuService {

	@Autowired
	MenuMapper mapper;
	@Override
	public Object getMenuTree() {
		// TODO Auto-generated method stub
		return mapper.selectList(null);
	}

}
