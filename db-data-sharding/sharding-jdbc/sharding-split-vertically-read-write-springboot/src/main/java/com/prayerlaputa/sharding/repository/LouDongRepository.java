package com.prayerlaputa.sharding.repository;

import java.util.List;

import com.prayerlaputa.sharding.po.LouDong;
import org.apache.ibatis.annotations.Mapper;



@Mapper
public interface LouDongRepository {

	Long addLouDong(LouDong louDong);

	List<LouDong> list();
}
