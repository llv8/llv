package com.ddou.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan("com.ddou.mapper")
public class MybatisConfig {
}
