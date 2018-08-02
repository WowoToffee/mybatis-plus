package com.wowotoffee.mybatisplus.Injector;

import com.baomidou.mybatisplus.mapper.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;

public class MyMetaObjectHandler extends MetaObjectHandler {

    @Override
    public void insertFill(MetaObject metaObject) {

        Object testType = getFieldValByName("name", metaObject);
        System.out.println("name=" + testType);
        if (testType == null) {
            setFieldValByName("name", "gaygay", metaObject);
        }
    }

    @Override
    public void updateFill(MetaObject metaObject) {

    }
}
