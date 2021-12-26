package cn.wolfcode.trip.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;


@Configuration//添加配置类
@PropertySource("classpath:core.properties")//加载配置文件扫描属性
@MapperScan("cn.wolfcode.trip.mapper")//扫描mapper
public class CoreConfig {
}
