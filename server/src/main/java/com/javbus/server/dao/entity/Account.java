package com.javbus.server.dao.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.time.LocalDateTime;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author Lee
 * @since 2020-04-11
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("obp_account")
public class Account extends Model<Account> {

    private static final long serialVersionUID = 1L;

    private Long id;
    /**
                * 账号
     */
    private String account;

    private String password;

    private String userName;

    private String idCard;

    private String phone;

    private String email;

    private String address;

    private LocalDateTime createdDate;

    private LocalDateTime updatedDate;

    private String salt;
    @Override
    protected Serializable pkVal() {
        return this.account;
    }

}
