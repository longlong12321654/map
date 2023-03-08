package com.hndist.cim;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 * CIM平台启动类
 * @author hnsh
 */
@SpringBootApplication
public class DeskTopServerApplication extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(DeskTopServerApplication.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(DeskTopServerApplication.class, args);
    }

}
