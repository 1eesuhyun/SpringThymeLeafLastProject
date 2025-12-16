package com.sist.web.vo;

import lombok.Data;

@Data
public class RecipeVO {
	/*
	 *  NO            NUMBER(38)     
		TITLE         VARCHAR2(4000) 
		POSTER        VARCHAR2(4000) 
		CHEF          VARCHAR2(4000) 
		LINK          VARCHAR2(26)   
		HIT           NUMBER(38)     
		LIKECOUNT     NUMBER(38)     
		JJIMCOUNT     NUMBER(38)     
		REPLYCOUNT    NUMBER(38) 
	 */
	private int no,hit,likecount,jjimcount,replycount;
	private String title,poster,chef,link;
}
