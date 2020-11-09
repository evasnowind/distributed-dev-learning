package com.prayerlaputa.sharding;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Spring Boot版 Sharding JDBC 垂直拆分（不同的表在不同的库中）案例
 * 
 * @author chenglong.yu
 */
@SpringBootApplication
public class ShardingDbApplication {
	public static void main(String[] args) {
		SpringApplication.run(ShardingDbApplication.class, args);
	}
}
