package com.sist.web.controller;
import java.util.*;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sist.web.vo.*;

import lombok.RequiredArgsConstructor;

import com.sist.web.service.*;
import com.sist.web.dao.*;
import com.sist.web.entity.BoardEntity;
@Controller
@RequiredArgsConstructor
public class BoardController {
	private final BoardRepository bdao;
	
	@GetMapping("/board/list")
	public String board_list(@RequestParam(name="page",required = false)String page,Model model)
	{
		if(page==null)
			page="1";
		int curpage=Integer.parseInt(page);
		int rowSize=10;
		int start=(curpage*rowSize)-rowSize;
		int end=curpage*rowSize;
		List<BoardVO> list=bdao.boardListData(start,end);
		int count=(int)bdao.count();
		int totalpage=(int)(Math.ceil(count/10.0));
		
		model.addAttribute("list", list);
		model.addAttribute("curpage", curpage);
		model.addAttribute("totalpage", totalpage);
		model.addAttribute("main_html", "board/list");
		
		return "main/main";
	}
	@GetMapping("/board/insert")
	public String board_insert(Model model) 
	{
		model.addAttribute("main_html", "board/insert");
		return "main/main";
	}
	@PostMapping("/board/insert_ok")
	public String board_insert_ok(@ModelAttribute("vo")BoardEntity vo)
	{
		vo.setHit(0);
		vo.setRegdate(new Date());
		vo.setNo(bdao.getMax());
		bdao.save(vo);
		return "redirect:/board/list";
	}
	@GetMapping("/board/detail")
	public String board_detail(@RequestParam("no")int no,Model model)
	{
		BoardEntity vo=bdao.findByNo(no);
		// 조회수 증가
		vo.setHit(vo.getHit()+1);
		bdao.save(vo);
		
		vo=bdao.findByNo(no);
		model.addAttribute("vo", vo);
		model.addAttribute("main_html", "board/detail");
		return "main/main";
	}
	@GetMapping("/board/update")
	public String board_update(@RequestParam("no")int no,Model model)
	{
		BoardUpdateVO vo=bdao.boardUpdateData(no);
		model.addAttribute("vo", vo);
		model.addAttribute("main_html", "board/update");
		return "main/main";
	}
	@PostMapping("/board/update_ok")
	public String board_update_ok(@ModelAttribute("vo") BoardEntity vo,Model model)
	{
		BoardEntity dbVO=bdao.findByNo(vo.getNo());
		String result="no";
		if(vo.getPwd().equals(dbVO.getPwd()))
		{
			vo.setNo(vo.getNo());
			vo.setHit(dbVO.getHit());
			bdao.save(vo);
			result="yes";
		}
		model.addAttribute("res", result);
		model.addAttribute("no", vo.getNo());
		return "board/update_ok";
	}
	@GetMapping("/board/delete")
	public String board_delete(@RequestParam("no")int no,Model model)
	{
		model.addAttribute("no", no);
		model.addAttribute("main_html", "board/delete");
		return "main/main";
	}
	@PostMapping("/board/delete_ok")
	public String board_delete_ok(@ModelAttribute("no")int no,@ModelAttribute("pwd")String pwd,Model model)
	{
		String res="no";
		BoardEntity vo=bdao.findByNo(no);
		if(pwd.equals(vo.getPwd()))
		{
			res="yes";
			bdao.delete(vo);
		}
		model.addAttribute("res", res);
		return "board/delete_ok";
	}
}
