package com.slw.pro;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.context.ApplicationContext;

/**
 * @ClassName: SunProApplication
 * @Description:
 * @Author: fan jin yang
 * @Date: 2020/11/17
 * @Version: 1.0.0
 **/
@Slf4j
@SpringBootApplication(scanBasePackages = "com.slw.pro", exclude = {DataSourceAutoConfiguration.class, DataSourceTransactionManagerAutoConfiguration.class})
public class SunProApplication {

    public static void main(String[] args) {
        log.info("start project...");
        try {
            ApplicationContext applicationContext = SpringApplication.run(SunProApplication.class, args);
            log.info("start project success!");
        } catch (Exception e) {
            log.error("start project error, e = ",e);
        }
    }
}
