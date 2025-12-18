package com.sist.web.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

import com.sist.web.vo.RecipeDetailVO;
/*
 *  > Mapper (SQL) ==> Service에서 구현 => Controller => HTML/JSP
 *                                          |
 *                                     데이터베이스에서 읽은 값을 브라우저
 *                                          | Spring에서 처리
 *                                            @Controller
 *                                              | ThymeLeaf / JSP
 */
import com.sist.web.vo.RecipeVO;
import com.sist.web.vo.SeoulVO;

public interface RecipeService {
	public List<RecipeVO> recipeListData(int start);
	public int recipeTotalPage();
	public RecipeDetailVO recipeDetailData(int no);
	public List<RecipeVO> recipeTop10();
	public List<RecipeVO> recipeChefListData(int start,String chef);
	public int recipeChefTotalPage(String chef);
}
