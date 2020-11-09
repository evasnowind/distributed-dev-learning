package com.prayerlaputa.sharding.service;

import java.util.List;


import com.prayerlaputa.sharding.po.LouDong;

public interface LouDongService {

	List<LouDong> list();
	
	Long addLouDong(LouDong louDong);
		
}
