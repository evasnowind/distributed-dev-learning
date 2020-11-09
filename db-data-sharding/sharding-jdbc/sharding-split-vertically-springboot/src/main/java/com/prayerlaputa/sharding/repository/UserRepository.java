package com.prayerlaputa.sharding.repository;

import java.util.List;

import com.prayerlaputa.sharding.po.User;
import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface UserRepository {
	
	Long addUser(User user);
	
	List<User> list();
	
	User findById(Long id);
	
	User findByName(String name);
}
