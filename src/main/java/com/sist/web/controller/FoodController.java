package com.sist.web.controller;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.*;
import com.sist.web.vo.*;

import lombok.RequiredArgsConstructor;

import com.sist.web.service.*;
@Controller
@RequiredArgsConstructor
public class FoodController {
	private final FoodService fservice;
	
	@GetMapping("/food/list")
	public String food_list(@RequestParam(name="page",required = false)String page,Model model)
	{
		if(page==null)
			page="1";
		int curpage=Integer.parseInt(page);
		// 현재 페이지 데이터 읽기
		int start=(curpage-1)*12;
		List<FoodVO> list=fservice.foodListData(start);
		// 0, 12, 24
		int totalpage=fservice.foodTotalPage();
		
		final int BLOCK=10;
		int startPage=((curpage-1)/BLOCK*BLOCK)+1;
		int endPage=((curpage-1)/BLOCK*BLOCK)+BLOCK;
		if(endPage>totalpage)
			endPage=totalpage;
		
		model.addAttribute("list", list);
		model.addAttribute("curpage", curpage);
		model.addAttribute("startPage", startPage);
		model.addAttribute("totalpage", totalpage);
		model.addAttribute("endPage", endPage);
		// include
		model.addAttribute("main_html", "food/list");
		return "main/main";
	}
}
