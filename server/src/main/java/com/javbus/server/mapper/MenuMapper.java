package com.javbus.server.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.javbus.server.dao.entity.Menu;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author yuan
 * @since 2019-10-18
 */
@Mapper
public interface MenuMapper extends BaseMapper<Menu> {

	List<Menu> selectAll();

}
