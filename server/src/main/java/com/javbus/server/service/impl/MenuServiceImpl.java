package com.javbus.server.service.impl;

import com.javbus.server.dao.entity.Menu;
import com.javbus.server.mapper.MenuMapper;
import com.javbus.server.service.MenuService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

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

	@Resource
	MenuMapper menuMapper;

	@Override
	public List<Menu> getMenuTree() {
		// TODO Auto-generated method stub
		return menuMapper.selectList(null);
	}

	@Override
	public Menu menuById(String id) {
		return menuMapper.selectById(Long.valueOf(id));
	}
}
