package com.sist.web.controller;
import java.util.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.sist.web.service.*;
import com.sist.web.vo.*;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
/*
 *  HttpServletRequest
 *  
 *  => 클래스 캡슐화
 *  class Model
 *  {
 *  	HttpServletRequest request; => DispatcherServlet
 *  	public void addAttribute(String key, Object obj)
 *  	{
 *  		request.setAttribued(key,obj);
 *  	}
 *  }
 *  
 *  @Controller
 *  @RestController
 *  ------------------- DispatcherServlet => 사용자 요청
 *  
 *  
 *  한개 page=1 @RequestParam("page") 하나씩 넘길 때
 *  VO 단위 => @ModelAttribute("vo") 데이터를 넘길 때 값이 전부 vo에 있는경우에 이런식으로
 *  {      => JSON을 객체로 변환 @RequestBody
 *  	no:1,
 * 		name:''
 *  }
 *  
 *  @ResponseBody   @RestController
 *       |                |
 *       ------------------
 *        | 다른 언어로 값을 전송
 *   메소드 => 승격 => 연산자
 *    free() / malloc()
 *     |         |
 *  delete      new
 *  
 *  XML => 변경(어노테이션)
 *  
 *  1. 전송 / 처리 / 결과값 출력
 *     -------------------
 *     | 요청        | 결과값
 */
@Controller
@RequiredArgsConstructor
@RequestMapping("/recipe/")
public class RecipeController {
	private final RecipeService rservice;
	
	@GetMapping("list")
	public String recipe_list(@RequestParam(name="page",required = false) String page,Model model)
	{
		if(page==null)
			page="1";
		int curpage=Integer.parseInt(page);
		// 현재 페이지 데이터 읽기
		int start=(curpage-1)*12;
		List<RecipeVO> list=rservice.recipeListData(start);
		// 0, 12, 24
		int totalpage=rservice.recipeTotalPage();
		
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
		model.addAttribute("main_html", "recipe/list");
		
		return "main/main";
	}
	@GetMapping("detail")
	public String recipe_detail(@RequestParam("no") int no,Model model,HttpSession session)
	{
		RecipeDetailVO vo=rservice.recipeDetailData(no);
		List<String> mList=new ArrayList<String>();
		List<String> nList=new ArrayList<String>();
		String[] datas=vo.getFoodmake().split("\n");
		for(String s:datas)
		{
			StringTokenizer st=new StringTokenizer(s,"^");
			mList.add(st.nextToken());
			nList.add(st.nextToken());
		}
		model.addAttribute("mList", mList);
		model.addAttribute("nList", nList);
		model.addAttribute("vo", vo);
		String id=(String)session.getAttribute("id");
		if(id==null)
		{
			model.addAttribute("sessionId", "");
		}
		else
		{
			model.addAttribute("sessionId", id);
		}
		// 댓글
		model.addAttribute("main_html", "recipe/detail");
		return "main/main";
	}
	@GetMapping("chef_list")
	public String recipe_chef_list(@RequestParam(name="page",required = false) String page,Model model,@RequestParam("chef") String chef)
	{
		
		if(page==null)
			page="1";
		int curpage=Integer.parseInt(page);
		// 현재 페이지 데이터 읽기
		int start=(curpage-1)*12;
		List<RecipeVO> list=rservice.recipeChefListData(start, chef);
		// 0, 12, 24
		int totalpage=rservice.recipeChefTotalPage(chef);
		
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
		model.addAttribute("chef", chef);
		model.addAttribute("main_html", "recipe/chef_list");
		return "main/main";
	}
}
