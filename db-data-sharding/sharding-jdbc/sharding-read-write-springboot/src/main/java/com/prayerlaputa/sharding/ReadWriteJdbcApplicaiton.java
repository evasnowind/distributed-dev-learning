package com.prayerlaputa.sharding;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Spring Boot版 Sharding JDBC 读写分离示列
 * 
 * @author chenglong.yu
 */
@SpringBootApplication
public class ReadWriteJdbcApplicaiton {
	public static void main(String[] args) {
		SpringApplication.run(ReadWriteJdbcApplicaiton.class, args);
	}
}
