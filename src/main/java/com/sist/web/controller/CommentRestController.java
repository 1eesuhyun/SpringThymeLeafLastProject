package com.sist.web.controller;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import lombok.RequiredArgsConstructor;
import java.util.*;
import com.sist.web.vo.*;

import jakarta.servlet.http.HttpSession;

import com.sist.web.service.*;

@RestController
@RequiredArgsConstructor
public class CommentRestController {
	private final CommentService cservice;
	
	public Map commonsData(int cno,int type)
	{
		Map map=new HashMap();
		List<CommentVO> list=cservice.commentListData(cno, type);
		map.put("list", list);
		map.put("cno", cno);
		map.put("type", type);
		
		return map;
	}
	@GetMapping("/comment/list_vue/")
	public ResponseEntity<Map> comment_list(@RequestParam("cno")int cno,@RequestParam("type")int type,Model model)
	{
		Map map=new HashMap();
		try
		{
			map=commonsData(cno, type);
		}catch(Exception ex)
		{
			return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(map,HttpStatus.OK);
	}
	@PostMapping("/comment/insert_vue/")
	public ResponseEntity<Map> comment_insert(@RequestBody CommentVO vo,HttpSession session)
	{
		Map map=new HashMap();
		try
		{
			String id=(String)session.getAttribute("id");
			String name=(String)session.getAttribute("name");
			vo.setId(id);
			vo.setName(name);
			cservice.commentInsert(vo);
			map=commonsData(vo.getCno(), vo.getType());
		}catch(Exception ex)
		{
			return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(map,HttpStatus.OK); 
	}
	@GetMapping("/comment/delete_vue/")
	public ResponseEntity<Map> comment_delete(@RequestParam("no") int no,@RequestParam("cno") int cno,@RequestParam("type") int type)
	{
		Map map=new HashMap();
		try
		{
			cservice.commentDelete(no);
			map=commonsData(cno,type);
		}catch(Exception ex)
		{
			return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(map,HttpStatus.OK);
	}
	@GetMapping("/comment/update_vue/")
	public ResponseEntity<Map> comment_update(@RequestParam("no") int no,@RequestParam("cno") int cno,@RequestParam("type") int type,@RequestParam("msg")String msg)
	{
		Map map=new HashMap();
		try
		{
			cservice.commentUpdate(no, msg);
			map=commonsData(cno,type);
		}catch(Exception ex)
		{
			return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(map,HttpStatus.OK);
	}
}
