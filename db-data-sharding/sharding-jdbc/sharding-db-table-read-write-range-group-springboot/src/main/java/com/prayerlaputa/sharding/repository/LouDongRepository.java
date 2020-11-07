package com.prayerlaputa.sharding.repository;

import com.prayerlaputa.sharding.po.LouDong;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


@Mapper
public interface LouDongRepository {
	
	Long addLouDong(LouDong louDong);
	
	List<LouDong> list();
}
