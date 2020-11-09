package com.prayerlaputa.sharding.service;

import java.util.List;

import com.prayerlaputa.sharding.po.User;

public interface UserService {

	List<User> list();
	
	Long add(User user);
	
	User findById(Long id);
	
	User findByName(String name);
	
}
