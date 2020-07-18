package com.javbus.server.dao.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;


/**
 * <p>
 * 
 * </p>
 *
 * @author yuan
 * @since 2019-10-18
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName(value="menu",resultMap = "BaseResultMap")
public class Menu extends Model<Menu> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String url;

    private String level;

    private Integer parentId;

    private LocalDateTime createdDate;

    private String createdBy;

    private LocalDateTime updatedDate;
    
    @TableField(exist = false)
    private List<Menu> childMenu;

    private LocalDateTime updatedBy;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
