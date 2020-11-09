package com.prayerlaputa.sharding;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Spring Boot版 Sharding JDBC 分库分表案例
 * 
 * @author chenglong.yu
 *
 * @about http://www.prayerlaputa.com
 */
@SpringBootApplication
public class ShardingDbTableApplication {
	public static void main(String[] args) {
		SpringApplication.run(ShardingDbTableApplication.class, args);
	}
}
