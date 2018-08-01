package com.wowotoffee.mybatisplus.entity;

import com.baomidou.mybatisplus.enums.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.mapper.SqlCondition;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author wowotoffee
 * @since 2018-08-01
 */
public class User extends Model<User> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    //condition = SqlCondition.LIKE 加入条件 以后进行where的时候 就是模糊查询
    @TableField(value = "user_name" ,condition = SqlCondition.LIKE)
    private String userName;
    private String password;
    private String email;
    private String name;
    @TableField("user_phone")
    private Integer userPhone;
    private String idcard;
    private Integer integral;
    @TableField("business_license")
    private String businessLicense;
    private String remark;
    @TableField("creation_time")
    private Date creationTime;
    @TableField("modify_time")
    private Date modifyTime;
    @TableField("delete_time")
    private Date deleteTime;
    @TableField("deleted_state")
    private Integer deletedState;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(Integer userPhone) {
        this.userPhone = userPhone;
    }

    public String getIdcard() {
        return idcard;
    }

    public void setIdcard(String idcard) {
        this.idcard = idcard;
    }

    public Integer getIntegral() {
        return integral;
    }

    public void setIntegral(Integer integral) {
        this.integral = integral;
    }

    public String getBusinessLicense() {
        return businessLicense;
    }

    public void setBusinessLicense(String businessLicense) {
        this.businessLicense = businessLicense;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Date getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(Date creationTime) {
        this.creationTime = creationTime;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    public Date getDeleteTime() {
        return deleteTime;
    }

    public void setDeleteTime(Date deleteTime) {
        this.deleteTime = deleteTime;
    }

    public Integer getDeletedState() {
        return deletedState;
    }

    public void setDeletedState(Integer deletedState) {
        this.deletedState = deletedState;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "User{" +
        ", id=" + id +
        ", userName=" + userName +
        ", password=" + password +
        ", email=" + email +
        ", name=" + name +
        ", userPhone=" + userPhone +
        ", idcard=" + idcard +
        ", integral=" + integral +
        ", businessLicense=" + businessLicense +
        ", remark=" + remark +
        ", creationTime=" + creationTime +
        ", modifyTime=" + modifyTime +
        ", deleteTime=" + deleteTime +
        ", deletedState=" + deletedState +
        "}";
    }
}
