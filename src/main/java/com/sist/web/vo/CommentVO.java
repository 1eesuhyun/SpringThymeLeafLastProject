package com.sist.web.vo;
import java.util.*;

import lombok.Data;
@Data
public class CommentVO {
/*
 *  NO      NOT NULL NUMBER         
	CNO              NUMBER         
	TYPE             NUMBER         
	ID               VARCHAR2(20)   
	NAME    NOT NULL VARCHAR2(51)   
	MSG     NOT NULL VARCHAR2(4000) 
	REGDATE          DATE  
 */
	private int no,cno,type;
	private String id,name,msg,dbday;
	private Date regdate;
	
}
