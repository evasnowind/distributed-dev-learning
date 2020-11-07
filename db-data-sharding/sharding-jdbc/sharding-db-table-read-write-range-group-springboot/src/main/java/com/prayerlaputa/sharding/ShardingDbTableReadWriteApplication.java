package com.prayerlaputa.sharding;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Spring Boot版 Sharding JDBC 分库分表+读写分离案例(范围分表+取模=无限扩容)
 * 
 * @author chenglong.yu
 */
@SpringBootApplication
public class ShardingDbTableReadWriteApplication {
	public static void main(String[] args) {
		SpringApplication.run(ShardingDbTableReadWriteApplication.class, args);
	}
}
