package com.javbus.server.service.impl;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.javbus.server.dao.entity.Role;
import com.javbus.server.mapper.RoleMapper;
import com.javbus.server.service.RoleService;

/**
 * <p>
 * 角色表 服务实现类
 * </p>
 *
 * @author Lee
 * @since 2020-04-18
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {

}
