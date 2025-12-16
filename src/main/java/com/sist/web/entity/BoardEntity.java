package com.sist.web.entity;
import java.util.*;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;
// save(), delete()
// findByno(int no) WHERE no=1
// findByName(String name)
// findByNameLike
// 단점 : JOIN / SubQjery를 지원 안함
@Data
@Entity(name="board_1")
public class BoardEntity {
	@Id
	private int no;
	
	private int hit;
	private String name,subject,content,pwd;
	private Date regdate;
}
