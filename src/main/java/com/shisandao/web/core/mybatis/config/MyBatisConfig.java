package com.shisandao.web.core.mybatis.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * MyBatis配置类
 * Created by 士三刀 on 2020/8/25.
 */
@Configuration
@MapperScan({"com.shisandao.web.core.sys.mapper"})
public class MyBatisConfig {
}
