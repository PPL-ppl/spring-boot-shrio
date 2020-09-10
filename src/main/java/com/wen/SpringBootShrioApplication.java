package com.wen;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.wen.mapper")
public class SpringBootShrioApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootShrioApplication.class, args);
    }

}
