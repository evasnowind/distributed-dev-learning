package com.prayerlaputa.sharding.service;

import com.prayerlaputa.sharding.po.LouDong;

import java.util.List;

public interface LouDongService {

	List<LouDong> list();
	
	Long addLouDong(LouDong louDong);
		
}
