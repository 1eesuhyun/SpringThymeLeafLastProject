package com.sist.web.vo;

import lombok.Data;

@Data
public class SeoulVO {
	/*
	 *  NO      NOT NULL NUMBER         
		TITLE   NOT NULL VARCHAR2(200)  
		POSTER  NOT NULL VARCHAR2(500)  
		MSG     NOT NULL VARCHAR2(4000) 
		ADDRESS NOT NULL VARCHAR2(300)  
		HIT              NUMBER   
	 */
	private int no,hit;
	private String title,poster,msg,address;
}
