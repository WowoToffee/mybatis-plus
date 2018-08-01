package com.wowotoffee.mybatisplus.config;

import com.baomidou.mybatisplus.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.plugins.PerformanceInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableTransactionManagement
@Configuration
@MapperScan("com.wowotoffee.mybatisplus.mapper")
public class MybatisPlusConfig {



    /**

     * mybatis-plus SQL执行效率插件【生产环境可以关闭】

     */

    @Bean

    public PerformanceInterceptor performanceInterceptor() {

        return new PerformanceInterceptor();

    }



    /*

     * 分页插件，自动识别数据库类型

     * 多租户，请参考官网【插件扩展】

     */

    @Bean

    public PaginationInterceptor paginationInterceptor() {

        PaginationInterceptor paginationInterceptor = new PaginationInterceptor();

        paginationInterceptor.setDialectType("mysql");

        return  paginationInterceptor;

    }







}


