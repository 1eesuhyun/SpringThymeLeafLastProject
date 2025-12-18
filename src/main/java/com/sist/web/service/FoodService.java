package com.sist.web.service;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.sist.web.vo.FoodVO;

public interface FoodService {
	public List<FoodVO> foodListData(int start);
	public int foodTotalPage();
	public List<FoodVO> foodTop10Data();
	public FoodVO foodDetailData(int fno);
	public FoodVO foodTop10();
}
