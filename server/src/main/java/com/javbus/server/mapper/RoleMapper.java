package com.javbus.server.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.javbus.server.dao.entity.Role;

/**
 * <p>
 * 角色表 Mapper 接口
 * </p>
 *
 * @author Lee
 * @since 2020-04-18
 */
@Mapper
public interface RoleMapper extends BaseMapper<Role> {

}
