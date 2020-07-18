package com.javbus.server.mapper;

import com.javbus.server.dao.entity.Account;

import org.apache.ibatis.annotations.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Lee
 * @since 2020-04-11
 */
@Mapper
public interface AccountMapper extends BaseMapper<Account> {

}
