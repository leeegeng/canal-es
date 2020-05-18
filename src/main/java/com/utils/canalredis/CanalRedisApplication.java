package com.utils.canalredis;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.utils.canalredis.mapper")
public class CanalRedisApplication {

    public static void main(String[] args) {
        SpringApplication.run(CanalRedisApplication.class, args);
    }

}
