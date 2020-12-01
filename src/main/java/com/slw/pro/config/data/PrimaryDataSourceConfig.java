package com.slw.pro.config.data;


import com.alibaba.druid.pool.DruidDataSource;
import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

/**
 * @ClassName: PrimaryDataSourceConfig
 * @Description:
 * @Author: fan jin yang
 * @Date: 2020/11/16
 * @Version: 1.0.0
 **/
@Configuration
@MapperScan(basePackages = "com.slw.pro.mapper")
public class PrimaryDataSourceConfig {

    @Value("${spring.datasource.db1.jdbc-url:}")
    private String jdbcUrl;

    @Value("${spring.datasource.db1.username}")
    private String userName;

    @Value("${spring.datasource.db1.password}")
    private String password;

    @Value("${spring.datasource.db1.driver-class-name}")
    private String driverClassName;


    // 数据源
    @Primary
    @Bean(name = "primaryDataSource")
    public DruidDataSource dataSource(){
        DruidDataSource druidDataSource = new DruidDataSource();
        druidDataSource.setUrl(jdbcUrl);
        druidDataSource.setUsername(userName);
        druidDataSource.setPassword(password);
        druidDataSource.setDriverClassName(driverClassName);
        return druidDataSource;
    }

    // session工厂
    @Bean
    @Primary
    public MybatisSqlSessionFactoryBean primarySqlSessionFactory(@Qualifier(value = "primaryDataSource") DataSource dataSource) throws Exception {
        MybatisSqlSessionFactoryBean bean = new MybatisSqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        return bean;
    }

    // 事务
    @Bean
    @Primary
    public DataSourceTransactionManager primaryTransactionManager(@Qualifier(value = "primaryDataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    // sessionTemplate
    @Bean("primarySqlSessionTemplate")
    @Primary
    public SqlSessionTemplate primarySqlSessionTemplate(SqlSessionFactory sqlSessionFactory) throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory);
    }


}
