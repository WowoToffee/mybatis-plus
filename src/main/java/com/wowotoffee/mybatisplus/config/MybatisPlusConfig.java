package com.wowotoffee.mybatisplus.config;

import com.baomidou.mybatisplus.MybatisConfiguration;
import com.baomidou.mybatisplus.MybatisXMLLanguageDriver;
import com.baomidou.mybatisplus.annotations.DataSource;
import com.baomidou.mybatisplus.entity.GlobalConfiguration;
import com.baomidou.mybatisplus.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.plugins.PerformanceInterceptor;
import com.baomidou.mybatisplus.spring.MybatisSqlSessionFactoryBean;
import com.baomidou.mybatisplus.spring.boot.starter.SpringBootVFS;
import com.wowotoffee.mybatisplus.Injector.MyInjector;
import com.wowotoffee.mybatisplus.Injector.MyMetaObjectHandler;
import org.aopalliance.intercept.Interceptor;
import org.apache.ibatis.mapping.DatabaseIdProvider;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.ResourceLoader;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

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

    /**
     * 配置定義全局操作
     * @return
     */
    @Bean
    public GlobalConfiguration globalConfiguration(){
        GlobalConfiguration globalConfiguration = new GlobalConfiguration();
        globalConfiguration.setMetaObjectHandler(handler());
        globalConfiguration.setSqlInjector(new MyInjector());
        return globalConfiguration;
    }

    /*@Bean
    public MybatisSqlSessionFactoryBean mybatisSqlSessionFactoryBean() {
        MybatisSqlSessionFactoryBean factoryBean = new MybatisSqlSessionFactoryBean();
        factoryBean.setGlobalConfig(globalConfiguration ());
        return factoryBean;
    }*/

    @Bean
    public MyMetaObjectHandler handler(){
        return new MyMetaObjectHandler();
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


