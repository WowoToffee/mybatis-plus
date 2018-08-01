package com.wowotoffee.mybatisplus.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author wowotoffee
 * @since 2018-08-01
 */
@TableName("user_role")
public class UserRole extends Model<UserRole> {

    private static final long serialVersionUID = 1L;

    @TableField("user_id")
    private Integer userId;
    @TableField("role_id")
    private Integer roleId;


    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    @Override
    protected Serializable pkVal() {
        return null;
    }

    @Override
    public String toString() {
        return "UserRole{" +
        ", userId=" + userId +
        ", roleId=" + roleId +
        "}";
    }
}
