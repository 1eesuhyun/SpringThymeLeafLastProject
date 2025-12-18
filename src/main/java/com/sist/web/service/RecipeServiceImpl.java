package com.sist.web.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.sist.web.mapper.RecipeMapper;
import com.sist.web.vo.RecipeDetailVO;
import com.sist.web.vo.RecipeVO;
import com.sist.web.vo.SeoulVO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RecipeServiceImpl implements RecipeService{
	private final RecipeMapper mapper;

	@Override
	public List<RecipeVO> recipeListData(int start) {
		// TODO Auto-generated method stub
		return mapper.recipeListData(start);
	}

	@Override
	public int recipeTotalPage() {
		// TODO Auto-generated method stub
		return mapper.recipeTotalPage();
	}

	@Override
	public RecipeDetailVO recipeDetailData(int no) {
		// TODO Auto-generated method stub
		mapper.recipeHitIncrement(no);
		return mapper.recipeDetailData(no);
	}

	@Override
	public List<RecipeVO> recipeTop10() {
		// TODO Auto-generated method stub
		return mapper.recipeTop10();
	}

	@Override
	public List<RecipeVO> recipeChefListData(int start, String chef) {
		// TODO Auto-generated method stub
		return mapper.recipeChefListData(start, chef);
	}

}
