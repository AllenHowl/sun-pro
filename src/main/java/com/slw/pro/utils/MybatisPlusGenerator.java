package com.slw.pro.utils;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

/**
 * @ClassName: MybatisPlusGenerator
 * @Description: MybatisPlus代码生成器
 * @Author: fan jin yang
 * @Date: 2020/11/16
 * @Version: 1.0.0
 **/
public class MybatisPlusGenerator {

    private static AutoGenerator autoGenerator;
    private static GlobalConfig globalConfig;
    private static DataSourceConfig dataSourceConfig;
    static {
        // 配置GlobalConfig
        globalConfig = new GlobalConfig();
        globalConfig.setOutputDir(System.getProperty("user.dir") + "/src/main/java");
        globalConfig.setAuthor("fjy");
        globalConfig.setOpen(false);
        globalConfig.setEntityName("%sEntity");
        globalConfig.setMapperName("%sMapper");
        globalConfig.setServiceImplName("%sServiceImpl");
        globalConfig.setServiceName("%sService");
        globalConfig.setControllerName("%sController");
        globalConfig.setXmlName("%sDao");

        // 配置DataSourceConfig
        dataSourceConfig = new DataSourceConfig();
        dataSourceConfig.setDbType(DbType.MYSQL);
        dataSourceConfig.setUrl("jdbc:mysql://rm-2zen0g2l5958x88y3so.mysql.rds.aliyuncs.com:3306/fjy_test");
        dataSourceConfig.setDriverName("com.mysql.cj.jdbc.Driver");
        dataSourceConfig.setUsername("fjy");
        dataSourceConfig.setPassword("fanjinyang");


        // 包配置
        PackageConfig pc = new PackageConfig();
        // 父路径
        pc.setParent("com.slw.pro");
        pc.setEntity("entity");
        pc.setMapper("mapper");
        pc.setService("service");
        pc.setServiceImpl("service.impl");
        pc.setController("controller");

        // 策略配置
        StrategyConfig strategyConfig = new StrategyConfig();
        strategyConfig.setNaming(NamingStrategy.underline_to_camel);
        // 数据库里的表
        strategyConfig.setInclude("user_info");

        // 填充
        autoGenerator = new AutoGenerator();
        autoGenerator.setGlobalConfig(globalConfig);
        autoGenerator.setDataSource(dataSourceConfig);
        autoGenerator.setPackageInfo(pc);
        autoGenerator.setStrategy(strategyConfig);
    }

    public static void main(String[] args) {
        autoGenerator.execute();
    }
}
