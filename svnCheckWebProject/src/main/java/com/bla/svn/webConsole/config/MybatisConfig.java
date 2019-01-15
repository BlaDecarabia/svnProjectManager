package com.bla.svn.webConsole.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @Author: 张巧
 * @Description:
 * @Date: Created in 2019/1/14 11:32
 * @Modified By:
 * @ModifiedDate:
 * @ModifiedDescription
 */
@Configuration
@EnableTransactionManagement
@MapperScan(basePackages = "com.bla.svn.webConsole.dao")
public class MybatisConfig {
}
